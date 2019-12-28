package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_delivery.*
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
    }

    override fun initListener() {

        tv_lt.setOnClickListener {
            val intent = Intent(this@DeliveryActivity,WebViewActivity::class.java)
            intent.putExtra("url","http://www.blueskyexpress.com.au/")
            intent.putExtra("type",2)
            startActivity(intent)
        }
        tv_cj.setOnClickListener {
            val intent = Intent(this@DeliveryActivity,WebViewActivity::class.java)
            intent.putExtra("url","http://www.changjiangexpress.com/")
            intent.putExtra("type",2)
            startActivity(intent)
        }
        tv_ewe.setOnClickListener {
            val intent = Intent(this@DeliveryActivity,WebViewActivity::class.java)
            intent.putExtra("url","https://www.ewe.com.au/")
            intent.putExtra("type",2)
            startActivity(intent)
        }
        tv_fy.setOnClickListener {
            val intent = Intent(this@DeliveryActivity,WebViewActivity::class.java)
            intent.putExtra("url","http://www.rlgaustralia.com/")
            intent.putExtra("type",2)
            startActivity(intent)
        }
    }
}