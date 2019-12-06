package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CollectionAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.CollectionBean
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionActivity:BaseActivity() {
    private val collectionAdapter: CollectionAdapter by lazy {
        CollectionAdapter()
    }
    override fun attachLayoutRes(): Int= R.layout.activity_collection

    override fun initData() {
        val list = mutableListOf<CollectionBean>()
        for (i in 1..20){
            list.add(CollectionBean("","卡姿兰带大眼睛","¥500",i.times(20)))
        }
        collectionAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text="我的收藏"
        iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            adapter=collectionAdapter
            layoutManager=LinearLayoutManager(this@CollectionActivity)
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
}