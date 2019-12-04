package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.OrderBean
import kotlinx.android.synthetic.main.activity_coupon.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/4
 */
class OrderAllActivity:BaseActivity() {
    private val orderAdapter:OrderAdapter by lazy {
        OrderAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_order_all

    override fun initData() {
       val list = mutableListOf<OrderBean>()
        for (i in 1..20){
            list.add(OrderBean(i%5,"订单号：1231312$i","http","黄金搭档$i",i,"规格：金色，大",5.times(i).toFloat()))
        }
        orderAdapter.addData(list)
    }

    override fun initView() {
      toolbar_title.text="全部订单"
        iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@OrderAllActivity)
            adapter=orderAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }
}