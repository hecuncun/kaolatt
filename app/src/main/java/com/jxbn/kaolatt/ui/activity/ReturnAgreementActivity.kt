package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/29
 */
class ReturnAgreementActivity:BaseActivity() {
    override fun attachLayoutRes(): Int= R.layout.activity_return_agreement

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="退换货声明"
    }

    override fun initListener() {
    }
}