package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.AddressAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.AddressBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class AddressListActivity : BaseActivity() {
    private val addressAdapter: AddressAdapter by lazy {
        AddressAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_address_list

    override fun initData() {
        val list = mutableListOf<AddressBean>()
        for (i in 0..10) {
            if (i == 0) {
                list.add(AddressBean("悟空", "123123132", "北京天安门", true))
            } else {
                list.add(AddressBean("悟空", "123123132", "北京天安门", false))
            }
        }
        addressAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text = "收货地址"
        toolbar_right_tv.text = "新增"
        toolbar_right_tv.visibility = View.VISIBLE
      //  iv_back.visibility = View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@AddressListActivity)
            adapter = addressAdapter
        }
        addressAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.iv_check -> {
//先全不选
                    if (adapter.data.size > 0) {
                        adapter.data.forEach {
                            (it as AddressBean).check = false
                        }
                    }
                    //选一个
                    ((adapter.getItem(position) as AddressBean)).check = true
                    addressAdapter.notifyDataSetChanged()
                }
                R.id.tv_delete -> {
                    val dialog = LogoutDialog(this@AddressListActivity)
                    dialog.show()
                    dialog.setTitle("确定要移除此收货地址？")
                    dialog.setConfirmListener(View.OnClickListener {
                        showToast("删除$position")
                        dialog.dismiss()
                    })
                }
                R.id.tv_edit -> {
                    jumpToAddAddressActivity(1)
                }
                else -> {
                }
            }
        }
    }

    override fun initListener() {
       // iv_back.setOnClickListener { finish() }
        toolbar_right_tv.setOnClickListener { jumpToAddAddressActivity(0) }
    }

    private fun jumpToAddAddressActivity(from:Int) {//0 新增 1修改
        val intent = Intent(this@AddressListActivity,AddAddressActivity::class.java)
        intent.putExtra("from",from)
        startActivity(intent)
    }
}