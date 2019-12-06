package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CollectionAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.CollectionBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionActivity:BaseActivity() {
    private val collectionAdapter: CollectionAdapter by lazy {
        CollectionAdapter()
    }
    override fun attachLayoutRes(): Int= R.layout.activity_collection

    val list = mutableListOf<CollectionBean>()
    override fun initData() {

        for (i in 1..20){
            list.add(CollectionBean("","卡姿兰带大眼睛","¥500",i.times(20),false))
        }
        collectionAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text="我的收藏"
        toolbar_right_tv.text="编辑"
        toolbar_right_tv.visibility=View.VISIBLE
        iv_back.visibility= View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            adapter=collectionAdapter
            layoutManager=LinearLayoutManager(this@CollectionActivity)
        }
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }

        toolbar_right_tv.setOnClickListener {
            if ( toolbar_right_tv.text == "编辑"){
                collectionAdapter.setShowCheckIcon(true)
                toolbar_right_tv.text="完成"
                tv_delete.visibility=View.VISIBLE
            }else if (toolbar_right_tv.text =="完成"){
                collectionAdapter.setShowCheckIcon(false)
                toolbar_right_tv.text="编辑"
                tv_delete.visibility=View.GONE
            }
        }

        collectionAdapter.setOnItemChildClickListener { adapter, view, position ->
           val collectionBean =(adapter.getItem(position) as CollectionBean)
            collectionBean.isChecked=!collectionBean.isChecked
            collectionAdapter.notifyItemChanged(position)
        }

        tv_delete.setOnClickListener {
            //删除所选商品
          val dialog =  LogoutDialog(this@CollectionActivity)
            dialog.show()
            dialog.setTitle("确定要移除所选商品？")
            dialog.setConfirmListener(View.OnClickListener {
                showToast("删除")
                dialog.dismiss()
            })
        }
    }
}