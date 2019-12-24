package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderDetailAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.OrderDetailBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshOrderListEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by heCunCun on 2019/12/5
 */
class OrderDetailActivity : BaseActivity() {
    private val orderAdapter: OrderDetailAdapter by lazy {
        OrderDetailAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_order_detail
    private var money =""
    private var orderGoodslList= mutableListOf<OrderDetailBean.DataBean.OrderGoodslListBean>()
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
                   money=t.data.priceTotalOrder.toString()
                   tv_order_no.text="订单编号:${t.data.orderNo}"
                   tv_create_time.text="创建时间:${t.data.createtime}"
                   orderGoodslList = t.data.orderGoodslList
                   orderAdapter.setNewData(orderGoodslList)
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
        tv_btn_left.setOnClickListener {
            jumpToWebViewActivity("",2)
        }

        tv_btn_middle.setOnClickListener {
            when(tv_btn_middle.text){
                "申请退换货"->{
                    jumpToReturnGoodsActivity()
                }

                "取消订单"->{
                    cancelOrder()
                }
            }

        }

        tv_confirm_right.setOnClickListener {
            when(tv_confirm_right.text){
                "立即付款"->{
                    val intent = Intent(this@OrderDetailActivity, PayActivity::class.java)
                    intent.putExtra("oid", oid)
                    intent.putExtra("money", money)
                    startActivity(intent)
                }
                "确认收货"->{
                    confirmReceiver()
                }
                "立即评价"->{
                    val intent = Intent(this@OrderDetailActivity, EvaluateActivity::class.java)
                    intent.putExtra("oid", oid)
                    intent.putExtra("name",orderGoodslList[0].name)
                    intent.putExtra("img", orderGoodslList[0].picture.split(",")[0])
                    intent.putExtra("mask", orderGoodslList[0].specs)
                    startActivity(intent)
                }
                "删除订单"->{
                    deleteOrder()
                }
            }
        }
    }

    /**
     * 删除
     */
    private fun deleteOrder() {
        val deleteOrderCall = SLMRetrofit.getInstance().api.deleteOrderCall(uid, oid)
        deleteOrderCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
            override fun onSucceed(t: BaseNoDataBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    showToast("删除订单成功")
                    EventBus.getDefault().post(RefreshOrderListEvent())
                    finish()
                } else {
                    showToast("删除订单失败:${t?.message}")
                }
            }

            override fun onFailed() {

            }
        })
    }

    /**
     * 确认收货
     */
    private fun confirmReceiver() {
        val confirmOrderCall = SLMRetrofit.getInstance().api.confirmOrderCall(uid, oid)
        confirmOrderCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
            override fun onSucceed(t: BaseNoDataBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    showToast("确认收货成功")
                    EventBus.getDefault().post(RefreshOrderListEvent())
                    finish()
                }else{
                    showToast("确认收货失败:${t?.message}")
                }
            }

            override fun onFailed() {
            }
        })
    }
    /**
     * 取消订单
     */
    private fun cancelOrder() {
        val cancelOrderCall = SLMRetrofit.getInstance().api.cancelOrderCall(uid, oid)
        cancelOrderCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
            override fun onSucceed(t: BaseNoDataBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    showToast("取消订单成功")
                    //刷新订单
                    EventBus.getDefault().post(RefreshOrderListEvent())
                    finish()

                } else {
                    showToast("取消订单失败:${t?.message}")
                }
            }

            override fun onFailed() {
            }
        })
    }

    /**
     * 物流信息
     */
    private fun jumpToWebViewActivity(url: String, type: Int) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("type", type)
        startActivity(intent)
    }
    //退货
    private fun jumpToReturnGoodsActivity() {
        val intent = Intent(this@OrderDetailActivity, ReturnGoodsActivity::class.java)
        intent.putExtra("oid",oid)
        intent.putExtra("name",orderGoodslList[0].name)
        intent.putExtra("img", orderGoodslList[0].picture.split(",")[0])
        intent.putExtra("mask", orderGoodslList[0].specs)
        startActivity(intent)
        finish()
    }


}