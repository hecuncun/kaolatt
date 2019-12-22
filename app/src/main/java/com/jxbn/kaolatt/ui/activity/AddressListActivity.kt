package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.AddressAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.AddressListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshAddressEvent
import com.jxbn.kaolatt.event.SetDefaultAddressEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by heCunCun on 2019/12/10
 */
class AddressListActivity : BaseActivity() {
    override fun useEventBus()=true

    private val addressAdapter: AddressAdapter by lazy {
        AddressAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_address_list

    private val list = mutableListOf<AddressListBean.DataBean>()
    override fun initData() {
        getAddressData()
    }

    private fun getAddressData() {
        val addressListCall = SLMRetrofit.getInstance().api.addressListCall(uid)
        addressListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<AddressListBean>() {
            override fun onSucceed(t: AddressListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    list.clear()
                    list.addAll(t.data)
                    addressAdapter.setNewData(t.data)
                }
            }

            override fun onFailed() {

            }
        })
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

            val dataBean = adapter.getItem(position) as AddressListBean.DataBean

            when (view.id) {
                R.id.iv_check -> {
//先全不选
                    if (adapter.data.size > 0) {
                        adapter.data.forEach {
                            (it as AddressListBean.DataBean).remark4 = ""
                        }
                    }
                    //选一个
                    dataBean.remark4 = "1"//设为默认
                    addressAdapter.notifyDataSetChanged()
                    //设置默认
                    val aid = dataBean.magorid
                    val addressDefaultCall = SLMRetrofit.getInstance().api.addressDefaultCall(aid, uid)
                    addressDefaultCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                        override fun onSucceed(t: BaseNoDataBean?) {
                            if (t?.code == Constant.SUCCESSED_CODE) {
                                showToast("设置默认地址成功")
                                EventBus.getDefault().post(SetDefaultAddressEvent())
                                finish()
                            } else {
                                showToast("设置默认地址失败：${t?.message}")
                            }
                        }

                        override fun onFailed() {
                        }
                    })

                }
                R.id.tv_delete -> {
                    val dialog = LogoutDialog(this@AddressListActivity)
                    dialog.show()
                    dialog.setTitle("确定要移除此收货地址？")
                    dialog.setConfirmListener(View.OnClickListener {
                        // showToast("删除$position")
                        //删除
//                        addressAdapter.remove(position)
                        dialog.dismiss()
                        //请求删除
                        val addressDeleteCall = SLMRetrofit.getInstance().api.addressDeleteCall(dataBean.magorid)
                        addressDeleteCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                            override fun onSucceed(t: BaseNoDataBean?) =
                                    if (t?.code == Constant.SUCCESSED_CODE) {
                                        showToast("删除成功")
                                        getAddressData()
                                    } else {
                                        showToast("删除失败:${t?.message}")
                                    }

                            override fun onFailed() {

                            }
                        })

                    })
                }
                R.id.tv_edit -> {
                    jumpToAddAddressActivity(1,dataBean)
                }
                else -> {
                }
            }
        }
    }

    override fun initListener() {
        // iv_back.setOnClickListener { finish() }
        toolbar_right_tv.setOnClickListener { jumpToAddAddressActivity(0,null) }
    }

    private fun jumpToAddAddressActivity(from: Int,bean: AddressListBean.DataBean?) {//0 新增 1修改
        val intent = Intent(this@AddressListActivity, AddAddressActivity::class.java)
        intent.putExtra("from", from)
        intent.putExtra("bean",bean)
        startActivity(intent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshList(event: RefreshAddressEvent) {
        getAddressData()
    }
}