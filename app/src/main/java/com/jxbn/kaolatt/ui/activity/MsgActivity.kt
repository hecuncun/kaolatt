package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.MsgAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.MsgBean
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
    override fun initData() {
        val list = mutableListOf<MsgBean>()
        for(i in 0..10){
            list.add(MsgBean("双12活动","2019-12-11-02 06:20","\"https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg",
                    "所以商品满100减20","来自平台"))
        }
        msgAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text="消息"
        iv_back.visibility = View.VISIBLE
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager =LinearLayoutManager(this@MsgActivity)
            adapter =msgAdapter
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
}