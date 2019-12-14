package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsBean
import kotlinx.android.synthetic.main.activity_goods_famous.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/12
 */
class GoodsFamousActivity:BaseActivity() {
    private val mAdapter:GoodsAdapter by lazy {
        GoodsAdapter()
    }
    override fun attachLayoutRes(): Int= R.layout.activity_goods_famous

    override fun initData() {
    }

    override fun initView() {
       toolbar_title.text="大牌推荐"
       // iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@GoodsFamousActivity, 2)
            adapter = mAdapter
           setHasFixedSize(true)
            isNestedScrollingEnabled=false
        }

        initRvData()
    }

    override fun initListener() {
      //  iv_back.setOnClickListener { finish() }
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(this@GoodsFamousActivity,GoodsDetailActivity::class.java)
            startActivity(intent)
        }
    }
    private val listGood = mutableListOf<GoodsBean>()
    private fun initRvData() {
        for (i in 0..19) {
            listGood.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
        mAdapter.addData(listGood)
    }
}