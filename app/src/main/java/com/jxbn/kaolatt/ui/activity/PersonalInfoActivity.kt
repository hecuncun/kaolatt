package com.jxbn.kaolatt.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.toolbar.*



/**
 * Created by hecuncun on 2019/12/3
 */
class PersonalInfoActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_personal_info

    override fun initData() {

    }

    override fun initView() {
       iv_back.visibility= View.VISIBLE

    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        iv_head_photo.setOnClickListener {
            showList()
        }
        ll_nick_name.setOnClickListener {
            jumpToNickNameActivity()
        }
    }

    private fun jumpToNickNameActivity() {
        val intent = Intent(this@PersonalInfoActivity,NickNameActivity::class.java)
        startActivity(intent)
    }

    private fun showList() {
        val items = arrayOf("拍照", "从相册中选择")
        val  builder = AlertDialog.Builder(this)
                .setItems(items, { _, i ->
                    Toast.makeText(this@PersonalInfoActivity, "你点击的内容为： " + items[i], Toast.LENGTH_LONG).show()
                    selectImage(i)
                })
        builder.create().show()
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
                    .forResult(PictureConfig.CHOOSE_REQUEST)
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
                            forResult(PictureConfig.CHOOSE_REQUEST)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    if (selectList.size > 0) {
                            GlideUtils.showCircle(iv_head_photo,selectList[0].compressPath,R.drawable.ic_launcher_background)
                    } else {
                        showToast("图片出现问题")
                    }
                }
            }
        }
    }
}