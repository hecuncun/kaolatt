package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.ScoreAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.UserScoreBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_score_detail.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/4
 */
class ScoreDetailActivity : BaseActivity() {
    /**
     * RecyclerView Divider
     */
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }

    private val scoreAdapter: ScoreAdapter by lazy {
        ScoreAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_score_detail

    override fun initData() {
        val list = mutableListOf<UserScoreBean>()
        for (i in 1..20) {
            list.add(UserScoreBean("2019-11-11 02:01:$i", "商品 $i", i))
        }
        scoreAdapter.addData(list)
        scoreAdapter.setOnItemClickListener { adapter, view, position ->
            showToast("$position")

        }
    }

    override fun initView() {
        toolbar_title.text = "积分明细"
        iv_back.visibility = View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@ScoreDetailActivity)
            adapter = scoreAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(recyclerViewItemDecoration)
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
}