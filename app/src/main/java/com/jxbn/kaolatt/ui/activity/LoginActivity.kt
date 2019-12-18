package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.UserInfoBean
import com.jxbn.kaolatt.event.LoginEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LoadingView
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by heCunCun on 2019/12/3
 */
class LoginActivity : BaseActivity() {

    override fun useEventBus(): Boolean = true

    override fun attachLayoutRes(): Int = R.layout.activity_login

    override fun initData() {


    }

    override fun initView() {


    }

    override fun initListener() {
        tv_register.setOnClickListener { jumpToRegisterActivity() }
        tv_forget_pwd.setOnClickListener { jumpToForgetPwdActivity() }
        tv_login.setOnClickListener { doLogin() }
        iv_eye.setOnClickListener { showOrHidden() }
        iv_clear.setOnClickListener { et_name.setText("") }

    }

    /**
     * 登录
     */
    private fun doLogin() {
        jumpToMainActivity()
        val name = et_name.text.toString().trim()
        val pwd = et_pwd.text.toString().trim()
        if (name.isNotEmpty() and pwd.isNotEmpty()) {
            val loadingView = LoadingView(this@LoginActivity)
            loadingView.setLoadingTitle("登录中...")
            loadingView.show()
            val observable = SLMRetrofit.getInstance().api.loginCall(name, pwd)
            observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackObserver<UserInfoBean>() {
                override fun onSucceed(t: UserInfoBean, desc: String?) {
                    showToast("登录成功")
                    uid = t.uid  //保存用户id
                    score = t.integral
                    nickname = t.nickname
                    photo = t.path
                    phone = t.phone
                    userNo =t.userNo
                    isLogin = true //登录状态
                    loadingView.dismiss()
                    // jumpToMainActivity()
                }

                override fun onFailed() {
                    loadingView.dismiss()
                    showToast("登陆失败")
                }
            })
        } else {
            showToast("请输入用户名和密码")
        }

    }

    /**
     * 密码显示隐藏
     */
    private var showOrHidden = false

    private fun showOrHidden() {
        if (et_pwd.text.toString().trim().isEmpty()) {
            return
        }
        if (showOrHidden) {
            showOrHidden = false
            //否则隐藏密码、
            iv_eye.setImageResource(R.mipmap.icon_login_unlook)
            et_pwd.transformationMethod = PasswordTransformationMethod.getInstance()
            //光标最后
            et_pwd.setSelection(et_pwd.text.toString().length)
        } else {
            showOrHidden = true
            //如果选中，显示密码
            iv_eye.setImageResource(R.mipmap.icon_login_look)
            et_pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            //光标最后
            et_pwd.setSelection(et_pwd.text.toString().length)
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginEvent(event: LoginEvent) {
        if (event.isLogin) {

            finish()

        } else {

        }
    }


}