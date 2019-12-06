package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CouponAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.CouponBean
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

    override fun initData() {
        val list = mutableListOf<CouponBean>()
        for (i in 1..20){
            list.add(CouponBean(10f.plus(i.times(5)),"满200可用","2019.12.02","2020.3.21",i%3))
        }
        couponAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text="我的优惠券"
        iv_back.visibility=View.VISIBLE
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
        iv_back.setOnClickListener { finish() }
    }
}