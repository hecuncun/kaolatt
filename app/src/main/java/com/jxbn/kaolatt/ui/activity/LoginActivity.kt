package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
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
        tv_login.setOnClickListener { jumpToMainActivity() }

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