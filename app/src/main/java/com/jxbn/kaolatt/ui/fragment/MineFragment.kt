package com.jxbn.kaolatt.ui.fragment
import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.ui.activity.CouponActivity
import com.jxbn.kaolatt.ui.activity.OrderAllActivity
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
        rl_coupon.setOnClickListener {  jumpToCouponActivity()}
        tv_order_all.setOnClickListener { jumpToOrderAllActivity(10) }
        tv_wait_pay.setOnClickListener { jumpToOrderAllActivity(0) }
        tv_wait_receive.setOnClickListener { jumpToOrderAllActivity(1) }
        tv_wait_evaluate.setOnClickListener { jumpToOrderAllActivity(2) }
        tv_complete.setOnClickListener { jumpToOrderAllActivity(3) }
        tv_reject.setOnClickListener {  jumpToOrderAllActivity(4) }

    }

    private fun jumpToScoreActivity() {
        val intent =Intent(activity,ScoreActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToSettingActivity() {
        val intent =Intent(activity,SettingActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToCouponActivity() {
        val intent =Intent(activity,CouponActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToOrderAllActivity(type:Int) {
        val intent =Intent(activity, OrderAllActivity::class.java)
        intent.putExtra("type",type)
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