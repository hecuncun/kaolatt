package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.RegisterBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/3
 */
class ForgetPwdActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_forget_pwd

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text = "忘记密码"
    }

    override fun initListener() {
        //获取验证码
        tv_code.setOnClickListener {
            //获得验证码
            if (et_phone.text.toString().isEmpty()) {
                showToast("请输入手机号")
                return@setOnClickListener
            }
            val observable = SLMRetrofit.getInstance().api.registerCodeCall(et_phone.text.toString(), 2)
            observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackObserver<RegisterBean>() {
                override fun onSucceed(t: RegisterBean?, desc: String?) {
                    showToast("验证码发送成功")
                }

                override fun onFailed() {

                }
            })
        }


        //修改密码

        tv_confirm.setOnClickListener {
            if (verify()) return@setOnClickListener
            val observable = SLMRetrofit.getInstance().api.resetPwdCall(et_phone.text.toString().trim(),et_code.text.toString().trim(),et_pwd.text.toString().trim())
            observable.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                override fun onSucceed(t: BaseNoDataBean?) {
                    if(t?.code==Constant.SUCCESSED_CODE){
                        showToast("重置密码成功")
                        finish()
                    }
                }

                override fun onFailed() {

                }
            })
        }


    }

    /**
     * 校验密码，手机号，code等填写
     */
    private fun verify(): Boolean {
        if (et_pwd.text.toString().trim().isEmpty() or et_pwd_confirm.text.toString().trim().isEmpty()
                or et_code.text.toString().trim().isEmpty() or et_phone.text.toString().trim().isEmpty()) {
            showToast("请将信息填写完整")
            return true
        }
        if (et_pwd.text.toString().trim() != et_pwd_confirm.text.toString().trim()) {
            showToast("两次输入密码不一致")
            return true
        }
        return false
    }

}