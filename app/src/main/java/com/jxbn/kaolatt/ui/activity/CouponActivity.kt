package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CouponAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.CouponListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_coupon.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/4
 */
class CouponActivity:BaseActivity() {
    private val couponAdapter:CouponAdapter by lazy {
        CouponAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_coupon
    val list = mutableListOf<CouponListBean.DataBean>()
    override fun initData() {
        val couponListCall = SLMRetrofit.getInstance().api.couponListCall(uid, 1)
        couponListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<CouponListBean>(){
            override fun onSucceed(t: CouponListBean?) {
               if (t?.code== Constant.SUCCESSED_CODE){
                   list.addAll(t.data)
                   couponAdapter.setNewData(list)
                   if (list.isEmpty()){
                       tv_no_data.visibility=View.VISIBLE
                   }else{
                       tv_no_data.visibility=View.GONE
                   }
               }
            }

            override fun onFailed() {
            }
        })

    }

    override fun initView() {
        toolbar_title.text="我的优惠券"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@CouponActivity)
            adapter=couponAdapter
            itemAnimator =DefaultItemAnimator()
        }
    }

    override fun initListener() {
    }
}