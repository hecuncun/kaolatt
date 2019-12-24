package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.ComImageAdapter
import com.jxbn.kaolatt.adapter.FullyGridLayoutManager
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.ImgBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshOrderListEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LoadingView
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_return_goods.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File

/**
 * Created by hecuncun on 2019/12/7
 */
class ReturnGoodsActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_return_goods
    private var oid=""
    private var name=""
    private var img=""
    private var mask=""
    override fun initData() {
        oid=intent.extras.getString("oid")
        name=intent.extras.getString("name")
        img=intent.extras.getString("img")
        mask=intent.extras.getString("mask")
        tv_name.text=name
        tv_mask.text="型号:$mask"
        GlideUtils.showRound(iv_goods, Constant.BASE_URL+img,R.mipmap.pic_good,6)
    }

    override fun initView() {
        toolbar_title.text = "申请退换货"
        initRvPhoto()
    }

    private var imageAdapter: ComImageAdapter? = null
    private var selectPhotoList = mutableListOf<LocalMedia>() //已选的照片集合
    private fun initRvPhoto() {
       val themeId = R.style.picture_default_style//相册的默认样式
        rv_photo.layoutManager = FullyGridLayoutManager(this@ReturnGoodsActivity, 5, GridLayoutManager.VERTICAL, false)
        imageAdapter = ComImageAdapter(this@ReturnGoodsActivity, onAddPicClickListener)
        imageAdapter!!.setList(selectPhotoList)
        imageAdapter!!.setSelectMax(5)
        rv_photo.adapter = imageAdapter!!
        imageAdapter!!.setOnItemClickListener { position, v ->
            if (selectPhotoList.size > 0) {
                val media = selectPhotoList.get(position)
                val pictureType = media.getPictureType()
                val mediaType = PictureMimeType.pictureToVideo(pictureType)
                when (mediaType) {
                    1 ->
                        // 预览图片 可自定长按保存路径
                        //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                        PictureSelector.create(this@ReturnGoodsActivity).themeStyle(themeId).openExternalPreview(position, selectPhotoList)
                    2 ->
                        // 预览视频
                        PictureSelector.create(this@ReturnGoodsActivity).externalPictureVideo(media.getPath())
                    3 ->
                        // 预览音频
                        PictureSelector.create(this@ReturnGoodsActivity).externalPictureAudio(media.getPath())
                }
        }


    }
    }

    private var pic =""
    override fun initListener() {
        tv_confirm.setOnClickListener {
        val content = et_content.text.toString().trim()
        val type =   if (rg_type.checkedRadioButtonId==R.id.rg_btn_1) 1 else 2
          if (content.isNotEmpty()){
              val returnOrderCall = SLMRetrofit.getInstance().api.returnOrderCall(uid, oid, type, content, pic)
              returnOrderCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                  override fun onSucceed(t: BaseNoDataBean?) {
                    if (t?.code==Constant.SUCCESSED_CODE){
                        showToast("提交成功")
                        EventBus.getDefault().post(RefreshOrderListEvent())
                        finish()
                    }else{
                        showToast("提交失败:${t?.message}")
                    }
                  }

                  override fun onFailed() {

                  }
              })
          }else{
              showToast("请填写原因")
          }
        }

    }

    private val onAddPicClickListener = ComImageAdapter.onAddPicClickListener {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this@ReturnGoodsActivity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(5)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(5)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectPhotoList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    selectPhotoList = PictureSelector.obtainMultipleResult(data)
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    if (selectPhotoList.size > 0) {
                        val loadingView = LoadingView(this@ReturnGoodsActivity)
                        loadingView.setLoadingTitle("上传中...")
                        loadingView.show()
                        val sb  =   StringBuilder()
                        var successNum = 0
                        for (i in selectPhotoList.indices){
                            //上传文件
                            val file = File(selectPhotoList[0].compressPath)
                            Logger.e("图片地址==${selectPhotoList[0].compressPath}")
                            val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                            //retrofit 上传文件api加上 @Multipart注解,然后下面这是个重点 参数1：上传文件的key，参数2：上传的文件名，参数3 请求头
                            val body: MultipartBody.Part = MultipartBody.Part.createFormData("upload", file.name, requestFile)
                            val uploadCall = SLMRetrofit.getInstance().api.uploadCall(body)
                            uploadCall.compose(ThreadSwitchTransformer()).subscribe(object: CallbackObserver<ImgBean>(){
                                override fun onSucceed(t: ImgBean?, desc: String?) {
                                    Logger.e("成功")
                                    Logger.e("网络图片地址==${t?.fileUrl}")
                                    sb.append(t?.fileUrl)
                                    sb.append(",")
                                    successNum++
                                    if (successNum==selectPhotoList.size){
                                        loadingView.dismiss()
                                        //进行拼接
                                        showToast("所有图片上传成功")
                                        pic= sb.toString().substring(0,sb.toString().length-1)
                                    }
                                }

                                override fun onFailed() {
                                    loadingView.dismiss()
                                    showToast("图片上传失败")
                                }
                            } )
                        }

                    } else {
                        showToast("图片出现问题")
                    }
                    imageAdapter!!.setList(selectPhotoList)
                    imageAdapter!!.notifyDataSetChanged()
                }
            }
        }
    }
}