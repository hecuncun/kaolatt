package com.jxbn.kaolatt.ui.fragment

import android.view.View
import com.jxbn.kaolatt.R
import com.lhzw.bluetooth.base.BaseFragment


/**
 * Created by hecuncun on 2019/11/13
 */
class MineFragment : BaseFragment() {
    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_mine

    override fun initView(view: View) {



    }


    override fun lazyLoad() {
    }
}