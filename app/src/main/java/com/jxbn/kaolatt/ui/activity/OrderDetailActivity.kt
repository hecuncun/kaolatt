package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderDetailAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.OrderDetailBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/5
 */
class OrderDetailActivity : BaseActivity() {
    private val orderAdapter: OrderDetailAdapter by lazy {
        OrderDetailAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_order_detail

    override fun initData() {
        val orderDetailCall = SLMRetrofit.getInstance().api.orderDetailCall(oid)
        orderDetailCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<OrderDetailBean>(){
            override fun onSucceed(t: OrderDetailBean?) {
               if (t?.code==Constant.SUCCESSED_CODE){
                   tv_name.text="收货人：${t.data.userAddress.name}"
                   tv_phone.text=t.data.userAddress.phone
                   tv_address.text="收货地址 : ${t.data.userAddress.area} ${t.data.userAddress.areaDetail}"
                   tv_price_total.text="¥${t.data.priceTotalGood}"
                   tv_price_you_hui.text="-¥${t.data.priceCard}"
                   tv_price_di_kou.text="-¥${t.data.priceInteger }"
                   tv_order_money.text="¥${t.data.priceTotalGood  }"
                   tv_pay_money.text="¥${t.data.priceTotalOrder } "
                   tv_order_no.text="订单编号:${t.data.orderNo}"
                   tv_create_time.text="创建时间:${t.data.createtime}"
                   orderAdapter.setNewData(t.data.orderGoodslList)
//1：待支付（取消订单），2：待发货（已支付），3：待收货（已发货），4：已收货（待评价），5：已完成（已评价），6：退换货中，7：退换货完成，8：取消申请中，9：已取消 ,
                   when(t.data.status){
                       1->{ tv_btn_left.visibility = View.GONE
                            tv_btn_middle.text="取消订单"
                            tv_confirm_right.text="立即付款"

                            tv_state.text="等待买家付款 "
                            tv_desc.visibility=View.VISIBLE
                            tv_desc.text="3天后自动关闭"
                          }
                       2,3->{
                           tv_btn_left.visibility = View.VISIBLE
                           tv_btn_left.text="查看物流"
                           tv_btn_middle.text="申请退换货"
                           tv_confirm_right.text="确认收货"

                           tv_state.text="等待买家收货 "
                           tv_desc.visibility=View.GONE
                       }
                       4->{
                           tv_btn_left.visibility = View.GONE
                           tv_btn_middle.text="申请退换货"
                           tv_confirm_right.text="立即评价"

                           tv_state.text="等待买家评价 "
                           tv_desc.visibility=View.GONE
                       }
                       5->{
                           tv_btn_left.visibility = View.GONE
                           tv_btn_middle.text="申请退换货"
                           tv_confirm_right.text="删除订单"

                           tv_state.text="订单已完成 "
                           tv_desc.visibility=View.GONE
                       }
                       6->{
                           tv_btn_left.visibility = View.GONE
                           tv_btn_middle.visibility = View.GONE
                           tv_confirm_right.visibility = View.GONE

                           tv_state.text="退货中 "
                           tv_desc.visibility=View.GONE
                       }
                       7->{
                           tv_btn_left.visibility = View.GONE
                           tv_btn_middle.visibility = View.GONE
                           tv_confirm_right.visibility = View.GONE

                           tv_state.text="退货完成"
                           tv_desc.visibility=View.GONE
                       }
                       else->{
                           tv_btn_left.visibility = View.GONE
                           tv_btn_middle.visibility = View.GONE
                           tv_confirm_right.visibility = View.GONE
                       }
                   }
               }
            }

            override fun onFailed() {

            }
        })

    }
    private var oid:String?=null

    override fun initView() {
        oid = intent.extras.getString("oid")
        toolbar_title.text = "订单详情"
       // iv_back.visibility = View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@OrderDetailActivity)
            adapter=orderAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun initListener() {
       // iv_back.setOnClickListener { finish() }
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