package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.OrderListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshNumEvent
import com.jxbn.kaolatt.event.RefreshOrderListEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LoadingView
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by hecuncun on 2019/12/4
 */
class OrderAllActivity : BaseActivity() {
    override fun useEventBus() = true

    private var type = 10
    private val orderAdapter: OrderAdapter by lazy {
        OrderAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_order_all

    private var currentPage = 1
    private var total = 0

    val list = mutableListOf<OrderListBean.DataBean.RowsBean>()

    override fun initData() {

        refreshData()

    }

    private fun refreshData() {

        loadingView?.show()
        list.clear()
        total = 0
        currentPage = 1
        getFirstData()
    }

    private fun getFirstData() {
        val orderListCall = SLMRetrofit.getInstance().api.orderListCall(currentPage, uid)
        orderListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<OrderListBean>() {
            override fun onSucceed(t: OrderListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    list.addAll(t.data.rows)
                    total = t.data.total
                    if (type == 10) {//全部
                        orderAdapter.setNewData(list)
                    } else if (type == 2) {//待收货
                        orderAdapter.setNewData(list.filter { it.status == 2 || it.status == 3 })
                    } else if (type == 6) {
                        orderAdapter.setNewData(list.filter {
                            it.status == 6 || it.status == 7 || it.status == 8
                        })
                    } else {
                        orderAdapter.setNewData(list.filter { it.status == type })
                    }

                    if (currentPage==total && orderAdapter.data.size == 0){
                        tv_no_data.visibility = View.VISIBLE
                        loadingView?.dismiss()
                    }else{
                        tv_no_data.visibility = View.GONE
                        if(orderAdapter.data.size==0){
                            currentPage++
                            //循环请求
                            getFirstData()//下页数据

                        } else{
                            loadingView?.dismiss()
                        }
                    }



                    //空白页
//                    if (total < 2) {
//                        if (orderAdapter.data.size == 0) {
//                            tv_no_data.visibility = View.VISIBLE
//                        } else {
//                            tv_no_data.visibility = View.GONE
//                        }
//
//                    } else {//多页数据0数据
//                       if(orderAdapter.data.size==0){
//                           currentPage++
//                           //循环请求
//                           getFirstData()//下页数据
//
//                       }
//
//                    }

                }

            }

            override fun onFailed() {
                loadingView?.dismiss()
            }
        })
    }

    private var loadingView:LoadingView?=null
    override fun initView() {
        type = intent.getIntExtra("type", 10)
        loadingView = LoadingView(this@OrderAllActivity)
        loadingView?.setLoadingTitle("")
        toolbar_title.text = when (type) {
            1 -> "待付款订单"
            2 -> "待收货订单"
            4 -> "待评价订单"
            5 -> "已完成订单"
            6 -> "退换货订单"
            else -> "全部订单"
        }
        // iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    override fun initListener() {
        //  iv_back.setOnClickListener { finish() }
        orderAdapter.setOnItemClickListener { adapter, view, position ->
            //详情页
            val intent = Intent(this@OrderAllActivity, OrderDetailActivity::class.java)
            intent.putExtra("oid", (adapter.data[position] as OrderListBean.DataBean.RowsBean).oid)
            startActivity(intent)
        }
        orderAdapter.setOnItemChildClickListener { adapter, view, position ->
            val rowsBean = adapter.data[position] as OrderListBean.DataBean.RowsBean
            when (view.id) {
                R.id.tv_confirm_order -> {//立即付款，确认收货，去评价，删除订单，查看详情
                    //1：待支付（取消订单），2：待发货（已支付），3：待收货（已发货），4：已收货（待评价），5：已完成（已评价），6：退换货中，7：退换货完成，8：取消申请中，9：已取消 ,
                    when (rowsBean.status) {
                        1 -> {//立即付款
                            val intent = Intent(this@OrderAllActivity, PayActivity::class.java)
                            intent.putExtra("oid", rowsBean.oid)
                            intent.putExtra("money", rowsBean.priceTotalOrder.toString())
                            startActivity(intent)
                        }

                        2, 3 -> {//确认收货
                            val confirmOrderCall = SLMRetrofit.getInstance().api.confirmOrderCall(uid, rowsBean.oid)
                            confirmOrderCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                                override fun onSucceed(t: BaseNoDataBean?) {
                                    if (t?.code==Constant.SUCCESSED_CODE){
                                        showToast("确认收货成功")
                                        EventBus.getDefault().post(RefreshNumEvent())
                                        refreshData()
                                    }else{
                                        showToast("确认收货失败:${t?.message}")
                                    }
                                }

                                override fun onFailed() {
                                }
                            })
                        }

                        4 -> {//去评价
                            val intent = Intent(this@OrderAllActivity, EvaluateActivity::class.java)
                            intent.putExtra("oid", rowsBean.oid)
                            intent.putExtra("name", rowsBean.orderGoodslList[0].name)
                            intent.putExtra("img", rowsBean.orderGoodslList[0].picture.split(",")[0])
                            intent.putExtra("mask", rowsBean.orderGoodslList[0].specs)
                            startActivity(intent)
                        }

                        5, 9 -> {//删除订单
                            val deleteOrderCall = SLMRetrofit.getInstance().api.deleteOrderCall(uid, rowsBean.oid)
                            deleteOrderCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                                override fun onSucceed(t: BaseNoDataBean?) {
                                    if (t?.code == Constant.SUCCESSED_CODE) {
                                        showToast("删除订单成功")
                                        EventBus.getDefault().post(RefreshNumEvent())
                                        refreshData()
                                    } else {
                                        showToast("删除订单失败:${t?.message}")
                                    }
                                }

                                override fun onFailed() {

                                }
                            })

                        }
                        6, 7, 8 -> {//查看详情
                            val intent = Intent(this@OrderAllActivity, OrderDetailActivity::class.java)
                            intent.putExtra("oid", rowsBean.oid)
                            startActivity(intent)
                        }

                    }


                }
                R.id.tv_cancel_order -> {
                    //取消订单，查看物流
                    //1：待支付（取消订单），2：待发货（已支付），3：待收货（已发货），4：已收货（待评价），5：已完成（已评价），6：退换货中，7：退换货完成，8：取消申请中，9：已取消 ,
                    when (rowsBean.status) {
                        1 -> {//取消订单
                            val cancelOrderCall = SLMRetrofit.getInstance().api.cancelOrderCall(uid, rowsBean.oid)
                            cancelOrderCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                                override fun onSucceed(t: BaseNoDataBean?) {
                                    if (t?.code == Constant.SUCCESSED_CODE) {
                                        showToast("取消订单成功")
                                        //刷新订单
                                        refreshData()

                                    } else {
                                        showToast("取消订单失败:${t?.message}")
                                    }
                                }

                                override fun onFailed() {
                                }
                            })

                        }
                        2, 3 -> {//查看物流
                            //jumpToWebViewActivity("", 2)
                            Intent(this@OrderAllActivity,DeliveryActivity::class.java).run{
                                startActivity(this)
                            }

                        }
                    }

                }

            }
        }

        //分页加载
        orderAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        orderAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
              if (total==currentPage){
                  orderAdapter.setEnableLoadMore(false)
              }
            currentPage++
            if (currentPage > total) {
                return@RequestLoadMoreListener
            }

            loadMoreData()

        }, recyclerView)
    }

    private fun loadMoreData() {
        val orderListCall = SLMRetrofit.getInstance().api.orderListCall(currentPage, uid)
        orderListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<OrderListBean>() {
            override fun onSucceed(t: OrderListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    if (type == 10) {//全部
                        orderAdapter.addData(t.data.rows)
                    } else if (type == 2) {//待收货
                        orderAdapter.addData(t.data.rows.filter { it.status == 2 || it.status == 3 })
                    } else if (type == 6) {
                        orderAdapter.addData(t.data.rows.filter {
                            it.status == 6 || it.status == 7 || it.status == 8
                        })
                    } else {
                        orderAdapter.addData(t.data.rows.filter { it.status == type })
                    }

                    if (currentPage == total) {
                        orderAdapter.loadMoreEnd()
                        orderAdapter.setEnableLoadMore(false)
                    } else {
                        orderAdapter.loadMoreComplete()
                        orderAdapter.setEnableLoadMore(true)
                    }
                }
            }

            override fun onFailed() {

            }
        })
    }

    private fun jumpToEvaluateActivity() {

    }

    private fun jumpToOrderDetailActivity() {

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@OrderAllActivity)
            adapter = orderAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun jumpToWebViewActivity(url: String, type: Int) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("type", type)
        startActivity(intent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshList(event: RefreshOrderListEvent) {
        refreshData()
    }
}