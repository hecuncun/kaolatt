package com.jxbn.kaolatt.ui.activity


import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class PaySuccessActivity:BaseActivity() {
    override fun attachLayoutRes(): Int= R.layout.activity_pay_success

    override fun initData() {
    }

    override fun initView() {
       toolbar_title.text="支付成功"
    }

    override fun initListener() {
    }
}