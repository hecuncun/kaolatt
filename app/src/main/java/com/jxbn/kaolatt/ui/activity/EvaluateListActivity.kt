package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.EvaluateBean
import com.jxbn.kaolatt.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_evaluate_list.*

/**
 * Created by heCunCun on 2019/12/9
 */
class EvaluateListActivity:BaseActivity() {
    private val evaluateAdapter: EvaluateAdapter by lazy {
        EvaluateAdapter()
    }
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }


    override fun attachLayoutRes(): Int= R.layout.activity_evaluate_list

    override fun initData() {
        val list = mutableListOf<EvaluateBean>()
        for (i in 0..20) {
            list.add(EvaluateBean("https://upload.jianshu.io/users/upload_avatars/9988193/fc26c109-1ae6-4327-a298-2def343e9cd8.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp",
                    "老子说", "谢谢博主的分享！"))
        }
        evaluateAdapter.addData(list)
    }

    override fun initView() {
        initRecyclerView()
    }

    override fun initListener() {

    }
    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@EvaluateListActivity)
            adapter = evaluateAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(recyclerViewItemDecoration)
            setHasFixedSize(true)
            isNestedScrollingEnabled=false
        }

    }
}