package com.jxbn.kaolatt.ui.fragment

import android.view.View
import com.jxbn.kaolatt.R
import com.lhzw.bluetooth.base.BaseFragment


/**
 * Created by hecuncun on 2019/11/13
 */
class ShoppingCartFragment : BaseFragment(){
    override fun initListener() {
    }

    companion object {
        fun getInstance():ShoppingCartFragment = ShoppingCartFragment()
    }
    override fun attachLayoutRes(): Int = R.layout.fragment_shopping_cart

    override fun initView(view: View) {
//        rl_step_num_container.setOnClickListener {
//            jumpToDailyStatisticsActivity()
//        }

    }

    private fun jumpToDailyStatisticsActivity() {
//        val intent = Intent(activity,DailyStatisticsActivity::class.java)
//        startActivity(intent)
    }

    override fun lazyLoad() {
    }
}