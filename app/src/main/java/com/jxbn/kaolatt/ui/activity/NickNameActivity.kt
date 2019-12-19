package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.UpdateInfoEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_nick_name.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by heCunCun on 2019/12/6
 */
class NickNameActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_nick_name

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text = "昵称"
    }

    override fun initListener() {
        tv_confirm.setOnClickListener {
            //调用修改头像接口
            if (et_name.text.toString().trim().isEmpty()){
                showToast("昵称不能为空")
                return@setOnClickListener
            }
            val updateInfoCall = SLMRetrofit.getInstance().api.updateInfoCall(uid, et_name.text.toString().trim(), null)
            updateInfoCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>(){
                override fun onSucceed(t: BaseNoDataBean?) {
                    if (t?.code== Constant.SUCCESSED_CODE){
                        showToast("修改成功")
                        nickname=et_name.text.toString().trim()
                        EventBus.getDefault().post(UpdateInfoEvent())
                        finish()
                    }else{
                        showToast("修改失败")
                    }
                }

                override fun onFailed() {
                }
            })

        }
    }
}