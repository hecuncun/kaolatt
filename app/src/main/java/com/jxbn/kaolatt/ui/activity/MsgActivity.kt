package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.MsgAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.MsgListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_msg.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/8
 */
class MsgActivity:BaseActivity(){
    private val msgAdapter:MsgAdapter by lazy {
        MsgAdapter()
    }
    override fun attachLayoutRes(): Int= R.layout.activity_msg
    private var currentPage = 1
    private var  total =1
    val list = mutableListOf<MsgListBean.DataBean.RowsBean>()
    override fun initData() {
        val msgListCall = SLMRetrofit.getInstance().api.msgListCall(currentPage, uid)
        msgListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<MsgListBean>(){
            override fun onSucceed(t: MsgListBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    list.addAll(t.data.rows)
                    msgAdapter.setNewData(list)
                    total=t.data.total
                }
            }

            override fun onFailed() {
            }
        })

    }

    override fun initView() {
        toolbar_title.text="消息"
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager =LinearLayoutManager(this@MsgActivity)
            adapter =msgAdapter
        }
    }

    override fun initListener() {
        msgAdapter.setOnItemClickListener { adapter, view, position ->
            //未读状态改为已读
            if (list[position].status.isEmpty()){
                val updateMsgStateCall = SLMRetrofit.getInstance().api.updateMsgStateCall(uid, list[position].mid)
                updateMsgStateCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                    override fun onSucceed(t: BaseNoDataBean?) {
                        if (t?.code==Constant.SUCCESSED_CODE){
                            list[position].status="readed"
                            adapter.notifyItemChanged(position)
                        }
                    }

                    override fun onFailed() {
                    }
                })
            }


            val intent = Intent(this@MsgActivity,WebViewActivity::class.java)
            intent.putExtra("url",list[position].content)
            intent.putExtra("type",1)
            startActivity(intent)

        }
        msgAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        msgAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if (total<2){
                msgAdapter.setEnableLoadMore(false)
            }
           currentPage++
            if (currentPage>total){
                return@RequestLoadMoreListener
            }
            val msgListCall = SLMRetrofit.getInstance().api.msgListCall(currentPage, uid)
            msgListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<MsgListBean>(){
                override fun onSucceed(t: MsgListBean?) {
                    if (t?.code==Constant.SUCCESSED_CODE){
                        list.addAll(t.data.rows)
                        msgAdapter.setNewData(list)
                        if (currentPage==total){
                            msgAdapter.loadMoreEnd()
                            msgAdapter.setEnableLoadMore(false)
                        }else{
                            msgAdapter.setEnableLoadMore(true)
                            msgAdapter.loadMoreComplete()
                        }
                    }
                }

                override fun onFailed() {
                }
            })


        },recyclerView)
    }

}