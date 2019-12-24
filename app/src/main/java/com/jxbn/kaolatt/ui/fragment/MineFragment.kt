package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.OrderListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.LoginEvent
import com.jxbn.kaolatt.event.UpdateInfoEvent
import com.jxbn.kaolatt.ext.startActivityCheckLogin
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.ui.activity.*
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by hecuncun on 2019/11/13
 */
class MineFragment : BaseFragment() {
    override fun useEventBus() = true
    override fun initListener() {
        rl_my_score.setOnClickListener { jumpToScoreActivity() }
        rl_setting.setOnClickListener { jumpToSettingActivity() }
        rl_coupon.setOnClickListener { jumpToCouponActivity() }
        tv_order_all.setOnClickListener { jumpToOrderAllActivity(10) }
        tv_wait_pay.setOnClickListener { jumpToOrderAllActivity(1) }
        tv_wait_receive.setOnClickListener { jumpToOrderAllActivity(2) }
        tv_wait_evaluate.setOnClickListener { jumpToOrderAllActivity(4) }
        tv_complete.setOnClickListener { jumpToOrderAllActivity(5) }
        tv_reject.setOnClickListener { jumpToOrderAllActivity(6) }
        rl_collection.setOnClickListener { jumpToCollectionActivity() }

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

    private fun jumpToOrderAllActivity(type: Int) {
        //type  1：待支付（取消订单），2：待收货 state=2 待发货（已支付）,state=3（已发货），4：已收货（待评价），
        // 5：已完成（已评价），6：退换货中，7：退换货完成，8：取消申请中，9：已取消
        if (isLogin) {
            val intent = Intent(activity, OrderAllActivity::class.java)
            intent.putExtra("type", type)
            startActivity(intent)
        } else {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun jumpToCollectionActivity() {
        activity!!.startActivityCheckLogin(CollectionActivity::class.java)
    }

    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_mine

    override fun initView(view: View) {
        tv_nick_name.text = if (isLogin) nickname else "未登录"
        tv_user_no.text = if (isLogin) "ID:$userNo" else "ID:------"

    }

    val list = mutableListOf<OrderListBean.DataBean.RowsBean>()
    val waitList = mutableListOf<OrderListBean.DataBean.RowsBean>()
    private var currentPage = 1
    private var total = 0
    override fun lazyLoad() {
        GlideUtils.showCircleWithBorder(iv_head_photo, Constant.BASE_URL + photo, R.mipmap.pic_head, Color.parseColor("#FFFFFF"))

        if (isLogin) {
            initWaitNum()
        }
    }

    private fun initWaitNum() {
        val orderListCall = SLMRetrofit.getInstance().api.orderListCall(currentPage, uid)
        orderListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<OrderListBean>() {
            override fun onSucceed(t: OrderListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    total = t.data.total
                    waitList.addAll(t.data.rows.filter { it.status == 2 || it.status == 3 })
                    if (waitList.size == 0) {
                        tv_num.visibility = View.GONE
                    } else {
                        tv_num.visibility = View.VISIBLE
                    }
                    tv_num.text = waitList.size.toString()
                    if (total > 1) {
                        for (i in 2..total) {
                            SLMRetrofit.getInstance().api.orderListCall(i, uid).compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<OrderListBean>() {
                                override fun onSucceed(t: OrderListBean?) {
                                    if (t?.code == Constant.SUCCESSED_CODE) {
                                        waitList.addAll(t.data.rows.filter { it.status == 2 || it.status == 3 })
                                        if (waitList.size == 0) {
                                            tv_num.visibility = View.GONE
                                        } else {
                                            tv_num.visibility = View.VISIBLE
                                        }
                                        tv_num.text = waitList.size.toString()
                                    }
                                }

                                override fun onFailed() {

                                }
                            })
                        }
                    }

                }
            }

            override fun onFailed() {

            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateInfoEvent(event: UpdateInfoEvent) {
        tv_nick_name.text = nickname
        GlideUtils.showCircleWithBorder(iv_head_photo, Constant.BASE_URL + photo, R.mipmap.pic_head, Color.parseColor("#FFFFFF"))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun initNum(event: LoginEvent) {
        initWaitNum()
    }


}