package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.RegisterBean
import com.jxbn.kaolatt.bean.UserInfoBean
import com.jxbn.kaolatt.event.LoginEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by heCunCun on 2019/12/3
 */
class RegisterActivity:BaseActivity() {
    private var agree = false
    override fun attachLayoutRes(): Int= R.layout.activity_register

    override fun initData() {

    }

    override fun initView() {
      toolbar_title.text="注册"

    }

    override fun initListener() {
       // iv_back.visibility = View.VISIBLE
//        iv_back.setOnClickListener {
//            finish()
//        }
        iv_agree.setOnClickListener {
            agree = !agree
            iv_agree.setImageDrawable(if (agree) resources.getDrawable(R.mipmap.icon_chenkbox) else resources.getDrawable(R.mipmap.icon_chenkbox_pre))
        }
        tv_agreement.setOnClickListener {
            jumpToAgreementActivity()
        }

        //获取验证码
        tv_code.setOnClickListener {
            //获得验证码
            if (et_phone.text.toString().isEmpty()) {
                showToast("请输入手机号")
                return@setOnClickListener
            }
            val observable = SLMRetrofit.getInstance().api.registerCodeCall(et_phone.text.toString(), 1)
            observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackObserver<RegisterBean>() {
                override fun onSucceed(t: RegisterBean?, desc: String?) {
                    showToast("验证码发送成功")
                }

                override fun onFailed() {

                }
            })
        }

        //注册操作
        tv_confirm.setOnClickListener {
            if (!agree){
                showToast("请先点击同意用户注册协议")
                return@setOnClickListener
            }

            if (verify()) return@setOnClickListener

            val observable = SLMRetrofit.getInstance().api.registerCall(et_phone.text.toString().trim(),et_code.text.toString().trim(),et_pwd.text.toString().trim())
            observable.compose(ThreadSwitchTransformer()).subscribe(object :CallbackObserver<UserInfoBean>(){
                override fun onSucceed(t: UserInfoBean?, desc: String?) {
                    isLogin=true //登录成功
                    t?.uid
                    t?.nickname
                    t?.phone
                    t?.pwd
                    t?.path//头像
                    t?.integral//积分
                    showToast("注册成功")
                    EventBus.getDefault().post(LoginEvent(true))
                    finish()
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

    private fun jumpToAgreementActivity() {
        val intent = Intent(this,AgreementActivity::class.java)
        startActivity(intent)
    }


}