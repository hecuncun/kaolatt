package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_score.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/3
 */
class ScoreActivity : BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_score

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text = "我的积分"
        toolbar_right_tv.text = "明细"
        toolbar_right_tv.visibility = View.VISIBLE
        tv_score.text=score.toString()
    }

    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            jumpToScoreDetailActivity()
        }
    }

    private fun jumpToScoreDetailActivity() {
        val intent = Intent(this@ScoreActivity, ScoreDetailActivity::class.java)
        startActivity(intent)
    }

}