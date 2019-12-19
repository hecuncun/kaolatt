package com.jxbn.kaolatt.ui.fragment
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.UpdateInfoEvent
import com.jxbn.kaolatt.ext.startActivityCheckLogin
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.ui.activity.*
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by hecuncun on 2019/11/13
 */
class MineFragment : BaseFragment() {
    override fun useEventBus()=true
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
        rl_collection.setOnClickListener {  jumpToCollectionActivity()}

    }

    private fun jumpToScoreActivity() {
        activity!!.startActivityCheckLogin(ScoreActivity::class.java)
    }
    private fun jumpToSettingActivity() {
        activity!!.startActivityCheckLogin(SettingActivity::class.java)
    }
    private fun jumpToCouponActivity() {
        activity!!.startActivityCheckLogin(CouponActivity::class.java)
    }
    private fun jumpToOrderAllActivity(type:Int) {
        val intent =Intent(activity, OrderAllActivity::class.java)
        intent.putExtra("type",type)
        startActivity(intent)
    }
    private fun jumpToCollectionActivity() {
        activity!!.startActivityCheckLogin(CollectionActivity::class.java)
    }

    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        tv_nick_name.text=if (isLogin) nickname else "未登录"
        tv_user_no.text =if (isLogin) "ID:$userNo" else  "ID:------"

    }


    override fun lazyLoad() {
        GlideUtils.showCircleWithBorder(iv_head_photo,Constant.BASE_URL+photo,R.mipmap.icon_kong,Color.parseColor("#FFFFFF"))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateInfoEvent(event: UpdateInfoEvent) {
        tv_nick_name.text=nickname
        GlideUtils.showCircleWithBorder(iv_head_photo,Constant.BASE_URL+photo,R.mipmap.icon_kong,Color.parseColor("#FFFFFF"))
    }



}