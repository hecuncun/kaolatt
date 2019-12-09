package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ScrollView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.EvaluateBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Created by heCunCun on 2019/12/9
 */
class GoodsDetailActivity : BaseActivity() {
    private val evaluateAdapter: EvaluateAdapter by lazy {
        EvaluateAdapter()
    }
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }

    override fun attachLayoutRes(): Int = R.layout.activity_goods_detail

    override fun initData() {
        val list = mutableListOf<EvaluateBean>()
        for (i in 0..1) {
            list.add(EvaluateBean("https://upload.jianshu.io/users/upload_avatars/9988193/fc26c109-1ae6-4327-a298-2def343e9cd8.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp",
                    "老子说", "谢谢博主的分享！"))
        }
        evaluateAdapter.addData(list)

    }

    override fun initView() {
        initTab()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@GoodsDetailActivity)
            adapter = evaluateAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(recyclerViewItemDecoration)
            setHasFixedSize(true)
            isNestedScrollingEnabled=false
        }

    }

    /**
     * 顶部选择器
     */
    private fun initTab() {
        tab_layout.addTab(tab_layout.newTab().setText("商品"))
        tab_layout.addTab(tab_layout.newTab().setText("评价"))
        tab_layout.addTab(tab_layout.newTab().setText("详情"))

        tab_layout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //选中tab\
                showToast(tab?.text.toString())
                when (tab?.text) {
                    "商品" -> {
                        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_UP) }
                    }
                    "评价" -> {
                        scrollView.post { scrollView.scrollTo(0, ll_evaluate_container.top) }
                    }
                    "详情" -> {
                        scrollView.post { scrollView.scrollTo(0, ll_detail_container.top) }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    override fun initListener() {
        tv_all_evaluate.setOnClickListener {
            val intent = Intent(this@GoodsDetailActivity,EvaluateListActivity::class.java)
            startActivity(intent)
        }
    }
}