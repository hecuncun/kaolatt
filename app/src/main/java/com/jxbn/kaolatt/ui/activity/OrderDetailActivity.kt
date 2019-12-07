package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/5
 */
class OrderDetailActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_order_detail

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text = "订单详情"
        iv_back.visibility = View.VISIBLE
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        tv_btn_middle.setOnClickListener {
            //退货
            jumpToReturnGoodsActivity()
        }
        tv_btn_left.setOnClickListener {
            jumpToDeliveryActivity()
        }
    }

    private fun jumpToDeliveryActivity() {
        val intent = Intent(this@OrderDetailActivity, DeliveryActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToReturnGoodsActivity() {
        val intent = Intent(this@OrderDetailActivity, ReturnGoodsActivity::class.java)
        startActivity(intent)
    }


}