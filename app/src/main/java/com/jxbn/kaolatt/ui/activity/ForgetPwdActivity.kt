package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/3
 */
class ForgetPwdActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_forget_pwd

    override fun initData() {

    }

    override fun initView() {
         toolbar_title.text="忘记密码"
        //iv_back.visibility= View.VISIBLE
    }

    override fun initListener() {
       // iv_back.setOnClickListener { finish() }
    }
}