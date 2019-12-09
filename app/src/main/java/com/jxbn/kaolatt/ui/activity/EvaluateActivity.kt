package com.jxbn.kaolatt.ui.activity

import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.R.id.iv_back
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/7
 */
class EvaluateActivity :BaseActivity(){
    override fun attachLayoutRes(): Int = R.layout.activity_evaluate

    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="评价"
        iv_back.visibility= View.VISIBLE
    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
    }
}