package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CartListAdapter
import com.jxbn.kaolatt.bean.CartBean
import com.jxbn.kaolatt.event.RefreshCarEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.ui.activity.ConfirmOrderActivity
import com.jxbn.kaolatt.ui.activity.LoginActivity
import com.jxbn.kaolatt.widget.LogoutDialog
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.litepal.LitePal
import org.litepal.extension.delete
import org.litepal.extension.findAll
import java.io.Serializable


/**
 * Created by hecuncun on 2019/11/13
 */
class ShoppingCartFragment : BaseFragment() {
    override fun useEventBus()=true
    private val cartListAdapter: CartListAdapter by lazy {
        CartListAdapter()
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            when (toolbar_right_tv.text) {
                "编辑" -> {
                    toolbar_right_tv.text = "完成"
                    list.forEach {
                        it.isCheck = false
                    }
                    cartListAdapter.notifyDataSetChanged()
                    //改变底部为删除
                    totalChecked = 0
                    tv_confirm.text = "移除"
                    checkbox.isChecked = false
                    tv_total_money.visibility = View.GONE
                    tv_confirm.background = resources.getDrawable(R.drawable.bg_red_right_round)
                }

                "完成" -> {
                    toolbar_right_tv.text = "编辑"
                    //改变底部为结算
                    tv_total_money.text = "合计：¥$totalMoney"
                    tv_confirm.text = "结算($totalChecked)"
                    tv_total_money.visibility = View.VISIBLE
                    tv_confirm.background = resources.getDrawable(R.drawable.bg_blue_right_round)
                }
            }
        }

        //全选
        checkbox.setOnCheckedChangeListener { _, check ->
            list.forEach {
                it.isCheck = check
            }
            cartListAdapter.notifyDataSetChanged()

            doCountAction()
        }

        tv_confirm.setOnClickListener {
            if (tv_confirm.text.contains("结算")) {
                if (isLogin){
                    if (totalChecked>0){
                        jumpToConfirmOrderActivity()
                    }else{
                        showToast("请先选中商品")
                    }
                }else{
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                }


            } else {
                val dialog = LogoutDialog(activity!!)
                dialog.show()
                dialog.setTitle("确定要移除所选商品？")
                dialog.setConfirmListener(View.OnClickListener {
                    showToast("删除")
                    dialog.dismiss()
                    //删除指定id的商品
                    list.forEach {
                        if (it.isCheck){
                          //  CommOperation.delete<CartBean>("goodsId",it.goodsId)
                            LitePal.delete<CartBean>(it.id)
                        }
                    }

                    //删除完就刷新
                    refreshList(RefreshCarEvent())


                })
            }
        }
    }

    private fun jumpToConfirmOrderActivity() {
        val intent = Intent(activity, ConfirmOrderActivity::class.java)
        //选中的bean列表 传递过去
        val orderList = list.filter { it.isCheck }//选中的商品传过去
        intent.putExtra("list",orderList as Serializable)
        intent.putExtra("total",totalMoney)
        intent.putExtra("num",totalChecked.toString())
        startActivity(intent)
    }

    private var totalChecked = 0
    private var totalMoney = 0.00
    private fun doCountAction() {
        //计算选中
        totalMoney = 0.00
        totalChecked = 0
        list.forEach {
            if (it.isCheck) {
                totalMoney += (it.price.toDouble()).times(it.num)
                totalChecked += it.num
            }
        }
        if (toolbar_right_tv.text.contains("编辑")) {
            tv_confirm.text = "结算($totalChecked)"
        } else {
            tv_confirm.text = "移除($totalChecked)"
        }
        tv_total_money.text = "合计：¥$totalMoney"

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
            val cartBean = (adapter.getItem(position) as CartBean)
            when (view.id) {
                R.id.iv_check -> {
                    cartBean.isCheck = !cartBean.isCheck
                    cartListAdapter.notifyItemChanged(position)
                    //计算总价
                    doCountAction()
                }
                R.id.tv_add -> {//加号
                    cartBean.num++
                    cartListAdapter.notifyItemChanged(position)
                    doCountAction()
                }
                R.id.tv_minus -> {
                    //
                    cartBean.num--
                    if (cartBean.num < 1) {
                        cartBean.num = 1
                    } else {
                        cartListAdapter.notifyItemChanged(position)
                    }
                    doCountAction()
                }
                else -> {

                }
            }
        }

    }

    private var list = mutableListOf<CartBean>()
    override fun lazyLoad() {
//        for (i in 0..10){
//            list.add(CartBean(i.toString(),"https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg","阿玛尼口红","201色号，大红","320",i+1,false))
//        }
         list = LitePal.findAll<CartBean>()
         cartListAdapter.setNewData(list)

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshList(event: RefreshCarEvent) {
        list =  LitePal.findAll<CartBean>()
        cartListAdapter.setNewData(list)
    }

}