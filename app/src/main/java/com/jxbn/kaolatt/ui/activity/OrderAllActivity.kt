package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.OrderListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
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

    private var currentPage = 1
    private var total = 0

    val list = mutableListOf<OrderListBean.DataBean.RowsBean>()

    override fun initData() {

        val orderListCall = SLMRetrofit.getInstance().api.orderListCall(currentPage,uid )
        orderListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<OrderListBean>(){
            override fun onSucceed(t: OrderListBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    list.addAll(t.data.rows)
                    total=t.data.total
                    if (type==10){//全部
                        orderAdapter.setNewData(list)
                    }else if (type==2){//待收货
                        orderAdapter.setNewData(list.filter { it.status==2 || it.status==3 })
                    }else if (type==6){
                        orderAdapter.setNewData(list.filter {
                            it.status==6 || it.status==7 || it.status==8
                        })
                    }else{
                        orderAdapter.setNewData(list.filter { it.status==type})
                    }
                    //空白页
                 if (orderAdapter.data.size==0){
                     tv_no_data.visibility= View.VISIBLE
                 }else{
                     tv_no_data.visibility= View.GONE
                 }
                }
            }

            override fun onFailed() {

            }
        })

    }

    override fun initView() {
        type = intent.getIntExtra("type",10)

        toolbar_title.text= when(type){
            1->"待付款订单"
            2->"待收货订单"
            4->"待评价订单"
            5->"已完成订单"
            6->"退换货订单"
            else->"全部订单"
        }
       // iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    override fun initListener() {
      //  iv_back.setOnClickListener { finish() }
        orderAdapter.setOnItemClickListener { adapter, view, position ->
            //详情页
            val intent = Intent(this@OrderAllActivity,OrderDetailActivity::class.java)
            intent.putExtra("oid",(adapter.data[position] as OrderListBean.DataBean.RowsBean).oid)
            startActivity(intent)
        }
        orderAdapter.setOnItemChildClickListener { adapter, view, position ->
         when(view.id){
             R.id.tv_confirm_order ->{//立即付款，确认收货，去评价，删除订单，查看详情
                 val rowsBean = adapter.data[position] as OrderListBean.DataBean.RowsBean
                 //1：待支付（取消订单），2：待发货（已支付），3：待收货（已发货），4：已收货（待评价），5：已完成（已评价），6：退换货中，7：退换货完成，8：取消申请中，9：已取消 ,
                 when(rowsBean.status){
                     1->{//立即付款
                         val intent = Intent(this@OrderAllActivity, PayActivity::class.java)
                         intent.putExtra("oid",rowsBean.oid)
                         intent.putExtra("money",rowsBean.priceTotalOrder.toString())
                         startActivity(intent)
                     }

                     2,3->{//确认收货

                     }

                     4->{//去评价
                         jumpToEvaluateActivity()
                     }

                     5,9->{//删除订单

                     }
                     6,7,8->{//查看详情

                     }

                 }




               }
                 R.id.tv_cancel_order->{
                     //取消订单，查看物流
                 }

         }
        }

       //分页加载
        orderAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        orderAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total==1){
                orderAdapter.setEnableLoadMore(false)
            }
            currentPage++
            if (currentPage>total){
                return@RequestLoadMoreListener
            }

            val orderListCall = SLMRetrofit.getInstance().api.orderListCall(currentPage, uid)
            orderListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<OrderListBean>(){
                override fun onSucceed(t: OrderListBean?) {
                    if (t?.code==Constant.SUCCESSED_CODE){
                        if (type==10){//全部
                            orderAdapter.addData(t.data.rows)
                        }else if (type==2){//待收货
                            orderAdapter.addData(t.data.rows.filter { it.status==2 || it.status==3 })
                        }else if (type==6){
                            orderAdapter.addData(t.data.rows.filter {
                                it.status==6 || it.status==7 || it.status==8
                            })
                        }else{
                            orderAdapter.addData(t.data.rows.filter { it.status==type})
                        }
                        //空白页
                        if (orderAdapter.data.size==0){
                            tv_no_data.visibility= View.VISIBLE
                        }else{
                            tv_no_data.visibility= View.GONE
                        }
                    }
                }

                override fun onFailed() {

                }
            })

        },recyclerView)
    }

    private fun jumpToEvaluateActivity() {
        val intent = Intent(this@OrderAllActivity,EvaluateActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToOrderDetailActivity() {

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@OrderAllActivity)
            adapter=orderAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }
}