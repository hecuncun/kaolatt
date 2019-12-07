package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.ComImageAdapter
import com.jxbn.kaolatt.adapter.FullyGridLayoutManager

import com.jxbn.kaolatt.base.BaseActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import kotlinx.android.synthetic.main.activity_return_goods.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/7
 */
class ReturnGoodsActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_return_goods

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text = "申请退换货"
        iv_back.visibility = View.VISIBLE
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

    override fun initListener() {
        iv_back.setOnClickListener { finish() }

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
                    for (media in selectPhotoList) {
                        // Log.i("图片-----》", media.getPath());
                    }
                    imageAdapter!!.setList(selectPhotoList)
                    imageAdapter!!.notifyDataSetChanged()
                }
            }
        }
    }
}