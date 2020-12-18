package com.jxbn.kaolatt.ui.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.AddressListBean
import com.jxbn.kaolatt.bean.ImgBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshAddressEvent
import com.jxbn.kaolatt.event.SetDefaultAddressEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.SelectDialog
import com.lljjcoder.Interface.OnCityItemClickListener
import com.lljjcoder.bean.CityBean
import com.lljjcoder.bean.DistrictBean
import com.lljjcoder.bean.ProvinceBean
import com.lljjcoder.citywheel.CityConfig
import com.lljjcoder.style.citypickerview.CityPickerView
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import java.io.File

/**
 * Created by heCunCun on 2019/12/10
 */
class AddAddressActivity : BaseActivity() {
    private var dialog: SelectDialog? = null
    private val REQUEST_FRONT = 0X333
    private val REQUEST_BACK = 0X334
    private var tag = REQUEST_FRONT

    private val cityPickerView by lazy {
        CityPickerView()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_add_address
    private var link2 = ""
    override fun initData() {
        val homeLinkCall = SLMRetrofit.getInstance().api.homeLinkCall(2)
        homeLinkCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
            override fun onSucceed(t: BaseNoDataBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    link2 = t.data as String
                }
            }

            override fun onFailed() {
            }
        })
    }

    private var from = 0
    private var dataBean: AddressListBean.DataBean? = null
    override fun initView() {
        cityPickerView.init(this)
        from = intent.extras.getInt("from")
        if (from == 0) {
            toolbar_title.text = "新增收货地址"
        } else {
            toolbar_title.text = "编辑收货地址"
            dataBean = intent.getParcelableExtra<AddressListBean.DataBean?>("bean")

            et_name.setText(dataBean?.name)
            et_phone.setText(dataBean?.phone)
            et_card.setText(dataBean?.card)
            tv_address.text = dataBean?.area
            et_address_detail.setText(dataBean?.areaDetail)


        }
        toolbar_right_tv.text = "购物须知"
        toolbar_right_tv.visibility = View.VISIBLE






        dialog = SelectDialog(this)
        dialog!!.setOnChoseListener { resId ->
            when (resId) {
                R.id.tv_camera -> selectImage(0)
                R.id.tv_photos -> selectImage(1)
                else -> {

                }
            }
        }
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            val intent = Intent(this@AddAddressActivity, WebViewActivity::class.java)
            intent.putExtra("url", link2)
            intent.putExtra("type", 3)
            startActivity(intent)
        }

        iv_id_card_front.setOnClickListener {
            tag = REQUEST_FRONT
            dialog?.show()
        }

        iv_id_card_back.setOnClickListener {
            tag = REQUEST_BACK
            dialog?.show()
        }

        ll_city_picker.setOnClickListener {
            //三级城市选择器
            wheel()
        }


        tv_confirm.setOnClickListener {
            if (et_name.text.toString().trim().isNotEmpty() && et_card.text.toString().trim().isNotEmpty()
                    && et_phone.text.toString().trim().isNotEmpty() && tv_address.text.isNotEmpty() && et_address_detail.text.toString().trim().isNotEmpty()) {
                if (from == 0) {//新增
                    val addAddressCall = SLMRetrofit.getInstance().api.addAddressCall(uid, et_name.text.toString().trim(), et_phone.text.toString().trim(), et_card.text.toString().trim(),
                            tv_address.text.toString(), cardPhotoZ, cardPhotoF, et_address_detail.text.toString().trim())
                    addAddressCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                        override fun onSucceed(t: BaseNoDataBean?) {
                            if (t?.code == Constant.SUCCESSED_CODE) {
                                showToast("添加地址成功")
                                //刷新上一个页面
                                EventBus.getDefault().post(RefreshAddressEvent())
                                EventBus.getDefault().post(SetDefaultAddressEvent())
                                finish()
                            } else {
                                showToast("添加地址失败:${t?.message}")
                            }
                        }


                        override fun onFailed() {
                        }
                    })
                } else {//修改
                    val addressUpdateCall = SLMRetrofit.getInstance().api.addressUpdateCall(dataBean?.magorid, et_name.text.toString().trim(), et_phone.text.toString().trim(), et_card.text.toString().trim(),
                            tv_address.text.toString(), cardPhotoZ, cardPhotoF, et_address_detail.text.toString().trim())

                    addressUpdateCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                        override fun onSucceed(t: BaseNoDataBean?) {
                            if (t?.code == Constant.SUCCESSED_CODE) {
                                showToast("修改地址成功")
                                //刷新上一个页面
                                EventBus.getDefault().post(RefreshAddressEvent())
                                finish()
                            } else {
                                showToast("修改地址失败:${t?.message}")
                            }
                        }

                        override fun onFailed() {
                        }
                    })

                }


            } else {
                showToast("请先将信息填写完整")
            }

        }


    }

    /**
     * 弹出选择器
     */
    // private val  cityPickerView =  CityPickerView()
    private fun wheel() {
        val cityConfig = CityConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(5)//显示个数
                .province("北京市")//省
                .city("北京市")//市
                .district("昌平区")//区
                .provinceCyclic(true)//循环显示
                .cityCyclic(true)//循环显示
                .districtCyclic(true)//循环显示
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                // * 显示省市区三级联动的显示状态
                //* PRO:只显示省份的一级选择器
                // * PRO_CITY:显示省份和城市二级联动的选择器
                // * PRO_CITY_DIS:显示省份和城市和县区三级联动的选择器
                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_city_name_tv)
                .setShowGAT(true)//显示港澳台数据
                .build()

        cityPickerView.setConfig(cityConfig)
        cityPickerView.setOnCityItemClickListener(object : OnCityItemClickListener() {
            override fun onSelected(province: ProvinceBean?, city: CityBean?, district: DistrictBean?) {
                val sb = StringBuilder()
                // sb.append("选择的结果：\n")
                if (province != null) {
                    sb.append(province.name + "-")
                }

                if (city != null) {
                    sb.append(city.name + "-")
                }

                if (district != null) {
                    sb.append(district.name)
                }

                tv_address.text = (sb.toString())

            }

            override fun onCancel() {

            }
        })
        cityPickerView.showCityPicker()
    }

    //选择图片方式

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

    private var cardPhotoZ: String? = null
    private var cardPhotoF: String? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                tag -> {
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    if (selectList.size > 0) {
                        if (tag == REQUEST_FRONT) {
                            GlideUtils.showRound(iv_id_card_front, selectList[0].compressPath, R.drawable.ic_launcher_background, 6)
                        } else {
                            GlideUtils.showRound(iv_id_card_back, selectList[0].compressPath, R.drawable.ic_launcher_background, 6)
                        }
                        //上传文件
                        val file = File(selectList[0].compressPath)
                        Logger.e("图片地址==${selectList[0].compressPath}")
                        val requestFile: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        //retrofit 上传文件api加上 @Multipart注解,然后下面这是个重点 参数1：上传文件的key，参数2：上传的文件名，参数3 请求头
                        val body: MultipartBody.Part = MultipartBody.Part.createFormData("upload", file.name, requestFile)
                        val uploadCall = SLMRetrofit.getInstance().api.uploadCall(body)
                        uploadCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackObserver<ImgBean>() {
                            override fun onSucceed(t: ImgBean?, desc: String?) {
                                Logger.e("成功")
                                Logger.e("网络图片地址==${t?.fileUrl}")
                                if (tag == REQUEST_FRONT) {
                                    cardPhotoZ = t?.fileUrl
                                } else {
                                    cardPhotoF = t?.fileUrl
                                }

                            }

                            override fun onFailed() {
                                Logger.e("失败")
                            }
                        })

                    } else {
                        showToast("图片出现问题")
                    }
                }
            }
        }
    }
}