package com.jxbn.kaolatt.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CartListAdapter
import com.jxbn.kaolatt.bean.CartBean
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * Created by hecuncun on 2019/11/13
 */
class ShoppingCartFragment : BaseFragment() {
    private val cartListAdapter: CartListAdapter by lazy {
        CartListAdapter()
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {

        }
    }

    companion object {
        fun getInstance(): ShoppingCartFragment = ShoppingCartFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_shopping_cart

    override fun initView(view: View) {
        toolbar_title.text = "购物车"
        toolbar_right_tv.text = "编辑"
        toolbar_right_tv.visibility = View.VISIBLE

        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = cartListAdapter
        }
        cartListAdapter.setOnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.iv_check->{
                    val cartBean =(adapter.getItem(position) as CartBean)
                    cartBean.isCheck=!cartBean.isCheck
                    cartListAdapter.notifyItemChanged(position)
                    //计算总价
                    if(toolbar_right_tv.text=="编辑"){
                        //计算底部
                    }
                }
            }
        }
        toolbar_right_tv.setOnClickListener {
            when(toolbar_right_tv.text){
                "编辑"->{
                    toolbar_right_tv.text="完成"
                    list.forEach {
                        (it as CartBean).isCheck=false
                    }
                    cartListAdapter.notifyDataSetChanged()
                    //改变底部为删除
                }

                "完成"->{
                    toolbar_right_tv.text="编辑"
                    //改变底部为结算
                }
            }
        }
    }

    val list = mutableListOf<CartBean>()
    override fun lazyLoad() {

        for (i in 0..10){
            list.add(CartBean("1231","阿玛尼口红","201色号，大红","￥320",1,false))
        }
        cartListAdapter.addData(list)

    }
}