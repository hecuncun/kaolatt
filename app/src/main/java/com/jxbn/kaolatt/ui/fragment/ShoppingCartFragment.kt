package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CartListAdapter
import com.jxbn.kaolatt.bean.CartBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.ui.activity.ConfirmOrderActivity
import com.jxbn.kaolatt.widget.LogoutDialog
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
            when(toolbar_right_tv.text){
                "编辑"->{
                    toolbar_right_tv.text="完成"
                    list.forEach {
                        it.isCheck=false
                    }
                    cartListAdapter.notifyDataSetChanged()
                    //改变底部为删除
                    totalChecked=0
                    tv_confirm.text="移除"
                    checkbox.isChecked=false
                    tv_total_money.visibility=View.GONE
                    tv_confirm.background=resources.getDrawable(R.drawable.bg_red_right_round)
                }

                "完成"->{
                    toolbar_right_tv.text="编辑"
                    //改变底部为结算
                    tv_total_money.text="合计：¥$totalMoney"
                    tv_confirm.text="结算($totalChecked)"
                    tv_total_money.visibility=View.VISIBLE
                    tv_confirm.background=resources.getDrawable(R.drawable.bg_blue_right_round)
                }
            }
        }

        //全选
        checkbox.setOnCheckedChangeListener { _, check ->
                list.forEach {
                    it.isCheck=check
                }
            cartListAdapter.notifyDataSetChanged()

            doCountAction()
        }

        tv_confirm.setOnClickListener {
            if (tv_confirm.text.contains("结算")){
                jumpToConfirmOrderActivity()
            }else{
                val dialog = LogoutDialog(activity!!)
                dialog.show()
                dialog.setTitle("确定要移除所选商品？")
                dialog.setConfirmListener(View.OnClickListener {
                    showToast("删除")
                    dialog.dismiss()
                })
            }
        }
    }

    private fun jumpToConfirmOrderActivity() {
        val intent =Intent(activity, ConfirmOrderActivity::class.java)
        startActivity(intent)
    }
    var totalChecked = 0
    var totalMoney = 0f
    private fun doCountAction() {
        //计算选中
        totalMoney = 0f
        totalChecked=0
        list.forEach {
            if(it.isCheck){
                totalMoney +=  (it.price.toFloat()).times(it.num)
                totalChecked+=it.num
            }
        }
        if (toolbar_right_tv.text.contains("编辑")){
            tv_confirm.text="结算($totalChecked)"
        }else{
            tv_confirm.text="移除($totalChecked)"
        }
        tv_total_money.text="合计：¥$totalMoney"

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
            val cartBean =(adapter.getItem(position) as CartBean)
            when(view.id){
                R.id.iv_check->{
                    cartBean.isCheck=!cartBean.isCheck
                    cartListAdapter.notifyItemChanged(position)
                    //计算总价
                    doCountAction()            }
                R.id.tv_add->{//加号
                    cartBean.num++
                    cartListAdapter.notifyItemChanged(position)
                    doCountAction()
                }
                R.id.tv_minus->{
                    //
                    cartBean.num--
                    if (cartBean.num<1){
                        cartBean.num=1
                    }else{
                        cartListAdapter.notifyItemChanged(position)
                    }
                    doCountAction()
                }
                else->{

                }
            }
        }

    }

    val list = mutableListOf<CartBean>()
    override fun lazyLoad() {
        for (i in 0..10){
            list.add(CartBean(i,"https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg","阿玛尼口红","201色号，大红","320",i+1,false))
        }
        cartListAdapter.addData(list)

    }
}