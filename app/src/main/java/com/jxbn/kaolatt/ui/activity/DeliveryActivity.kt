package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/8
 */
class DeliveryActivity:BaseActivity() {
    override fun attachLayoutRes(): Int= R.layout.activity_delivery

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="物流信息"
     //   iv_back.visibility= View.VISIBLE
    }

    override fun initListener() {
      //  iv_back.setOnClickListener { finish() }
    }
}