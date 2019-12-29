package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsMoreAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsMoreListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_goods_famous.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/12
 */
class GoodsFamousActivity : BaseActivity() {
    private var bcid=""
    private val mAdapter: GoodsMoreAdapter by lazy {
        GoodsMoreAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_goods_famous

    override fun initData() {
        initRvData()
    }

    override fun initView() {
        toolbar_title.text = "大牌推荐"
        bcid = intent.extras.getString("bcid")
        val picture = intent.extras.getString("picture")
        GlideUtils.showRound(iv_picture, Constant.BASE_URL + picture, R.mipmap.pic_banner, 8)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@GoodsFamousActivity, 2)
            adapter = mAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }

    override fun initListener() {
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(this@GoodsFamousActivity, GoodsDetailActivity::class.java)
            intent.putExtra("gid", listFamous[position].gid)
            startActivity(intent)
        }

        mAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        mAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total==1){
                mAdapter.setEnableLoadMore(false)
                return@RequestLoadMoreListener
            }
            currentPage++
            if (currentPage > total) {
                return@RequestLoadMoreListener
            }

            val famousMoreListCall = SLMRetrofit.getInstance().api.famousMoreListCall(currentPage, bcid, 1)
            famousMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<GoodsMoreListBean>(){
                override fun onSucceed(t: GoodsMoreListBean?) {
                    if (t?.code==Constant.SUCCESSED_CODE){
                        mAdapter.addData(t.data.rows)
                       if (currentPage==total){
                           mAdapter.loadMoreEnd()
                           mAdapter.setEnableLoadMore(false)
                       }else{
                           mAdapter.loadMoreComplete()
                           mAdapter.setEnableLoadMore(true)
                       }

                    }
                }

                override fun onFailed() {
                }
            })
        },recyclerView)
    }

    private val listFamous = mutableListOf<GoodsMoreListBean.DataBean.RowsBean>()
    private var currentPage =1
    private var total = 1
    private fun initRvData() {
        val famousMoreListCall = SLMRetrofit.getInstance().api.famousMoreListCall(currentPage, bcid, 1)
        famousMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<GoodsMoreListBean>(){
            override fun onSucceed(t: GoodsMoreListBean?) {
              if (t?.code==Constant.SUCCESSED_CODE){
                  if (t.data.rows.size==0){
                      iv_empty.visibility= View.VISIBLE
                  }else{
                      iv_empty.visibility= View.GONE
                  }
                  total=t.data.total
                  listFamous.addAll(t.data.rows)
                  mAdapter.setNewData(listFamous)
              }
            }

            override fun onFailed() {
            }
        })
    }
}