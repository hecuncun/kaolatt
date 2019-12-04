package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.ui.activity.ScoreActivity
import com.jxbn.kaolatt.ui.activity.SettingActivity
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * Created by hecuncun on 2019/11/13
 */
class MineFragment : BaseFragment() {
    override fun initListener() {
        rl_my_score.setOnClickListener { jumpToScoreActivity() }
        rl_setting.setOnClickListener { jumpToSettingActivity() }
    }

    private fun jumpToScoreActivity() {
        val intent =Intent(activity,ScoreActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToSettingActivity() {
        val intent =Intent(activity,SettingActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_mine

    override fun initView(view: View) {

    }


    override fun lazyLoad() {
    }


}