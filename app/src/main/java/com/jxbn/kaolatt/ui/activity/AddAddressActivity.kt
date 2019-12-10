package com.jxbn.kaolatt.ui.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.widget.SelectDialog
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class AddAddressActivity:BaseActivity() {
    private var dialog : SelectDialog?=null
    private val REQUEST_FRONT = 0X333
    private val REQUEST_BACK = 0X334
    private var tag =REQUEST_FRONT

    override fun attachLayoutRes(): Int= R.layout.activity_add_address

    override fun initData() {
    }

    override fun initView() {
        if(intent.extras.getInt("from")==0){
            toolbar_title.text="新增收货地址"
        }else{
            toolbar_title.text="编辑收货地址"
        }
        toolbar_right_tv.text="购物须知"
        toolbar_right_tv.visibility= View.VISIBLE
        iv_back.visibility=View.VISIBLE


        dialog = SelectDialog(this)
        dialog!!.setOnChoseListener{ resId->
            when(resId){
                R.id.tv_camera->selectImage(0)
                R.id.tv_photos->selectImage(1)
                else ->{

                }
            }
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        toolbar_right_tv.setOnClickListener {  }

        iv_id_card_front.setOnClickListener{
            tag=REQUEST_FRONT
            dialog?.show()
        }

        iv_id_card_back.setOnClickListener {
            tag=REQUEST_BACK
            dialog?.show()
        }

    }

    private fun selectImage(i: Int) {
        if (i == 0) {
            PictureSelector.create(this)
                    .openCamera(PictureMimeType.ofImage())
                    .enableCrop(true)// 是否裁剪 true or false
                    .compress(true)// 是否压缩 true or false
                    .withAspectRatio(3, 2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .minimumCompressSize(200)// 小于100kb的图片不压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .isDragFrame(false)// 是否可拖动裁剪框(固定)
                    .forResult(tag)
        } else {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage()) //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(1)// 最大图片选择数量 int
                    .imageSpanCount(3)
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .enableCrop(true)// 是否裁剪 true or false
                    .compress(true)// 是否压缩 true or false
                    .withAspectRatio(3, 2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .minimumCompressSize(200)// 小于100kb的图片不压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true).// 裁剪是否可放大缩小图片 true or false
                            isDragFrame(false).// 是否可拖动裁剪框(固定)
                            forResult(tag)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                tag -> {
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    if (selectList.size > 0) {
                        if(tag==REQUEST_FRONT){
                            GlideUtils.showRound(iv_id_card_front,selectList[0].compressPath,R.drawable.ic_launcher_background,6)
                        }else{
                            GlideUtils.showRound(iv_id_card_back,selectList[0].compressPath,R.drawable.ic_launcher_background,6)
                        }

                    } else {
                        showToast("图片出现问题")
                    }
                }
            }
        }
    }
}