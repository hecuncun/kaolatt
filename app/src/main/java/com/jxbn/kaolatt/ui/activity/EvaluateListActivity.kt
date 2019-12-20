package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.EvaluateListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
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
   private val listEvaluate = mutableListOf<EvaluateListBean.DataBean.RowsBean>()
    private var currentPage =1
    private var total =1
    var gid ="123"
    override fun initData() {
        val evaluateListCall = SLMRetrofit.getInstance().api.evaluateListCall(currentPage, gid)
        evaluateListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<EvaluateListBean>(){
            override fun onSucceed(t: EvaluateListBean?) {
                if (t?.code== Constant.SUCCESSED_CODE){
                    listEvaluate.addAll(t.data.rows)
                    evaluateAdapter.setNewData(listEvaluate)
                    total=t.data.total
                }
            }

            override fun onFailed() {
            }
        })

    }

    override fun initView() {
        initRecyclerView()
    }

    override fun initListener() {
        evaluateAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        evaluateAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total<2){
                evaluateAdapter.setEnableLoadMore(false)
            }
            currentPage++
            if (currentPage>total){
                return@RequestLoadMoreListener
            }

            val evaluateListCall = SLMRetrofit.getInstance().api.evaluateListCall(currentPage, gid)
            evaluateListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<EvaluateListBean>(){
                override fun onSucceed(t: EvaluateListBean?) {
                    if (t?.code== Constant.SUCCESSED_CODE){
                        evaluateAdapter.addData(t.data.rows)
                        if (currentPage==total){
                            evaluateAdapter.setEnableLoadMore(false)
                            evaluateAdapter.loadMoreEnd()
                        }else{
                            evaluateAdapter.setEnableLoadMore(true)
                            evaluateAdapter.loadMoreComplete()
                        }
                    }
                }

                override fun onFailed() {
                }
            })

        },recyclerView)
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