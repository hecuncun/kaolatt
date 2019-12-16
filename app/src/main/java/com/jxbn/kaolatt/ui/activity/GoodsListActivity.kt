package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodMoreListAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodMoreListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_goods_list.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/14
 */
class GoodsListActivity :BaseActivity() {
    private val goodAdapter: GoodMoreListAdapter by lazy {
        GoodMoreListAdapter()
    }
    override fun attachLayoutRes(): Int = R.layout.activity_goods_list

    override fun initData() {
        //分页查
        initRvData()

    }

    override fun initView() {
        toolbar_title.text="精品好物"
        initRecyclerView()
    }

    override fun initListener() {
        goodAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        //上拉加载更多
        goodAdapter.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                currentPage++
                if (currentPage > total) {
                    return
                }
                val goodMoreListCall = SLMRetrofit.getInstance().api.goodMoreListCall(currentPage)
                goodMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<GoodMoreListBean>(){
                    override fun onSucceed(t: GoodMoreListBean?) {
                        if (t?.code==Constant.SUCCESSED_CODE){
                            goodAdapter.addData(t.data.rows)
                            if (currentPage==total){
                                goodAdapter.loadMoreEnd()
                                goodAdapter.setEnableLoadMore(false)
                            }else{
                                goodAdapter.loadMoreComplete()
                                goodAdapter.setEnableLoadMore(true)
                            }

                        }
                    }

                    override fun onFailed() {

                    }
                })
            }

        },recyclerView)
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@GoodsListActivity, 2)
            adapter = goodAdapter
        }

    }
    private var currentPage =1
    private var listGood = mutableListOf<GoodMoreListBean.DataBean.RowsBean>()
    private var total =1
    private fun initRvData() {
        val goodMoreListCall = SLMRetrofit.getInstance().api.goodMoreListCall(currentPage)
        goodMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<GoodMoreListBean>(){
            override fun onSucceed(t: GoodMoreListBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    listGood=t.data.rows
                    goodAdapter.addData(listGood)
                    total=t.data.total
                }
            }

            override fun onFailed() {

            }
        })

    }


}