package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.GridLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsBean
import kotlinx.android.synthetic.main.activity_goods_list.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/14
 */
class GoodsListActivity :BaseActivity() {
    private val goodAdapter:GoodsAdapter by lazy {
        GoodsAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_goods_list

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="精品好物"
        initRecyclerView()
    }

    override fun initListener() {
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@GoodsListActivity, 2)
            adapter = goodAdapter
        }
        initRvData()
    }
    private val listGood = mutableListOf<GoodsBean>()
    private fun initRvData() {

        for (i in 0..50) {
            listGood.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
        goodAdapter.addData(listGood)
    }


}