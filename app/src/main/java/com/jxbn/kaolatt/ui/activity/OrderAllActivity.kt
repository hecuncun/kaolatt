package com.jxbn.kaolatt.ui.activity

import android.content.Intent
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
    private var type = 10
    private val orderAdapter:OrderAdapter by lazy {
        OrderAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_order_all

    override fun initData() {

       val list = mutableListOf<OrderBean>()
        for (i in 1..20){
            if (type==10){
                list.add(OrderBean(i%5,"订单号：1231312$i","http","黄金搭档$i",i,"规格：金色，大",5.times(i).toFloat()))
            }else{
                list.add(OrderBean(type,"订单号：1231312$i","http","黄金搭档$i",i,"规格：金色，大",5.times(i).toFloat()))
            }

        }
        orderAdapter.addData(list)
    }

    override fun initView() {
        type = intent.getIntExtra("type",10)

        toolbar_title.text= when(type){
            0->"待付款订单"
            1->"待收货订单"
            2->"待评价订单"
            3->"已完成订单"
            4->"退货订单"
            else->"全部订单"
        }
        iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        orderAdapter.setOnItemClickListener { adapter, view, position ->
            jumpToOrderDetailActivity()
        }
        orderAdapter.setOnItemChildClickListener { adapter, view, position ->
         when(view.id){
             R.id.tv_confirm_order ->{//立即付款，确认收货，去评价，删除订单，查看详情
                 jumpToEvaluateActivity()
               }
                 R.id.tv_cancel_order->{
                     //取消订单，查看物流
                 }

         }
        }
    }

    private fun jumpToEvaluateActivity() {
        val intent = Intent(this@OrderAllActivity,EvaluateActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToOrderDetailActivity() {
        val intent = Intent(this@OrderAllActivity,OrderDetailActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@OrderAllActivity)
            adapter=orderAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }
}