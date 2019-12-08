package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.GridLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.flowtag.FlowTagLayout
import com.jxbn.kaolatt.flowtag.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by hecuncun on 2019/12/8
 */
class SearchActivity:BaseActivity() {
    private val goodAdapter: GoodsAdapter by lazy {
        GoodsAdapter()
    }
    private val oldTagAdapter:TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }
    private val hotTagAdapter:TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }
    override fun attachLayoutRes(): Int = R.layout.activity_search

    private val list = mutableListOf<GoodsBean>()
    override fun initData() {
        for (i in 0..3) {
            list.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
        goodAdapter.addData(list)
    }

    override fun initView() {
        initTagLayout()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = goodAdapter
        }
    }

    /**
     * 标签初始化
     */
    private fun initTagLayout() {
        val list= mutableListOf<String>()
        for (i in 0..3){
            list.add("标签$i")
        }

        old_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE)//不选
        old_tag.adapter = oldTagAdapter
        oldTagAdapter.clearAndAddAll(list)
        old_tag.setOnTagClickListener { parent, view, position ->
            showToast(list[position])
        }

        hot_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE)//单选
        hot_tag.adapter = hotTagAdapter
        hotTagAdapter.clearAndAddAll(list)
        hot_tag.setOnTagClickListener { parent, view, position ->
            showToast(list[position])
        }
    }

    override fun initListener() {
        iv_del.setOnClickListener {
            et_search.setText("")
        }
        iv_search.setOnClickListener { //搜索
         val goodsName = et_search.text.toString().trim()
           showToast(if(goodsName.isEmpty()) "不能为空" else goodsName)
            // }
    }
    }
}