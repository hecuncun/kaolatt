package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.ScoreAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.ScoreListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
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

    private var currentPage = 1
    private var total =1
    val list = mutableListOf<ScoreListBean.DataBean.RowsBean>()
    override fun initData() {

        val scoreListCall = SLMRetrofit.getInstance().api.scoreListCall(currentPage, uid)
        scoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<ScoreListBean>(){
            override fun onSucceed(t: ScoreListBean?) {
              if (t?.code== Constant.SUCCESSED_CODE){
                   list.addAll(t.data.rows)
                  total=t.data.total
                  scoreAdapter.setNewData(list)
                  if (list.isEmpty()){
                      tv_no_data.visibility= View.VISIBLE
                  }else{
                      tv_no_data.visibility= View.GONE
                  }
              }
            }

            override fun onFailed() {
            }
        })


    }

    override fun initView() {
        toolbar_title.text = "积分明细"
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
        scoreAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total==1){
                scoreAdapter.setEnableLoadMore(false)
                scoreAdapter.loadMoreEnd()
            }
            currentPage++
            if (currentPage>total){return@RequestLoadMoreListener}
            val scoreListCall = SLMRetrofit.getInstance().api.scoreListCall(currentPage, tempUid)
            scoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<ScoreListBean>(){
                override fun onSucceed(t: ScoreListBean?) {
                    if (t?.code== Constant.SUCCESSED_CODE){
                        scoreAdapter.addData(t.data.rows)
                       if (currentPage==total){
                           scoreAdapter.setEnableLoadMore(false)
                           scoreAdapter.loadMoreEnd()
                       }else{
                           scoreAdapter.setEnableLoadMore(true)
                           scoreAdapter.loadMoreComplete()
                       }
                    }
                }

                override fun onFailed() {
                }
            })


        },recyclerView)
//        scoreAdapter.setOnItemClickListener { adapter, view, position ->
//            showToast("$position")
//
//        }
    }
}