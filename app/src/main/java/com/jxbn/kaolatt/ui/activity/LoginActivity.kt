package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.R.id.*
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.UserInfo
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LoadingView
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by heCunCun on 2019/12/3
 */
class LoginActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_login

    override fun initData() {


    }

    override fun initView() {


    }

    override fun initListener() {
        tv_register.setOnClickListener { jumpToRegisterActivity() }
        tv_forget_pwd.setOnClickListener { jumpToForgetPwdActivity() }
        tv_login.setOnClickListener { doLogin() }

    }

    /**
     * 登录
     */
    private fun doLogin() {
        val name = et_name.text.toString().trim()
        val pwd = et_pwd.text.toString().trim()
        if (name.isNotEmpty() and pwd.isNotEmpty()){
            val loadingView = LoadingView(this@LoginActivity)
            loadingView.setLoadingTitle("登录中...")
            loadingView.show()
            val observable = SLMRetrofit.getInstance().api.loginCall(name, pwd)
            observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackObserver<UserInfo>() {
                override fun onSucceed(t: UserInfo?, desc: String?) {
                   showToast("${t?.token}")
                    loadingView.dismiss()
                    jumpToMainActivity()
                }

                override fun onFailed() {
                    loadingView.dismiss()
                    showToast("登陆失败")
                }
            })
        }else{
            showToast("请输入用户名和密码")
        }

    }

    private fun jumpToRegisterActivity() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToForgetPwdActivity() {
        val intent = Intent(this@LoginActivity, ForgetPwdActivity::class.java)
        startActivity(intent)
    }

}