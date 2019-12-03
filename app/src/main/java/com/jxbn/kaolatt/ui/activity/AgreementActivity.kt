package com.jxbn.kaolatt.ui.activity

import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/3
 */
class AgreementActivity :BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_agreement

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="用户注册协议"
        iv_back.visibility= View.VISIBLE

    }

    override fun initListener() {
        iv_back.setOnClickListener {finish()}
    }
}