package com.jxbn.kaolatt.ui.activity

import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/5
 */
class OrderDetailActivity :BaseActivity() {
    override fun attachLayoutRes(): Int =R.layout.activity_order_detail

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="订单详情"
        iv_back.visibility= View.VISIBLE
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
}