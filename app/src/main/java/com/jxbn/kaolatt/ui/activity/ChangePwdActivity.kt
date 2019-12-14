package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/4
 */
class ChangePwdActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_change_pwd

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="密码修改"
        //iv_back.visibility=View.VISIBLE
    }

    override fun initListener() {
      //  iv_back.setOnClickListener { finish() }
    }
}