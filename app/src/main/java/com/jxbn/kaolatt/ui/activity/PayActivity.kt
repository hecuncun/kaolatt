package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.event.PayResultEvent
import com.jxbn.kaolatt.pay.AlipayUtils
import com.jxbn.kaolatt.pay.WXPayUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by heCunCun on 2019/12/10
 */
class PayActivity : BaseActivity() {
    override fun useEventBus()=true
    private var oid = ""
    private var money = ""
    private var isCheckZfb = true
    override fun attachLayoutRes(): Int = R.layout.activity_pay

    override fun initData() {


    }

    override fun initView() {
        toolbar_title.text = "订单支付"
        //iv_back.visibility= View.VISIBLE
        oid = intent.getStringExtra("oid")//订单id
        money = intent.getStringExtra("money")//订单金额
        Logger.e("oid==$oid")
        tv_pay_money.text = "待支付金额：¥$money"
    }

    override fun initListener() {
        //  iv_back.setOnClickListener{finish()}
        ll_check_zfb.setOnClickListener {
            if (!isCheckZfb) {
                iv_check_wechat.setImageResource(R.mipmap.icon_choose)
                iv_check_zfb.setImageResource(R.mipmap.icon_choose_pre)
                isCheckZfb = true
            }

        }
        ll_check_wechat.setOnClickListener {
            if (isCheckZfb) {
                iv_check_wechat.setImageResource(R.mipmap.icon_choose_pre)
                iv_check_zfb.setImageResource(R.mipmap.icon_choose)
                isCheckZfb = false
            }
        }

        tv_pay.setOnClickListener {
            jumpToPaySuccessActivity()
        }
    }

    private fun jumpToPaySuccessActivity() {

        if (isCheckZfb) {
            AlipayUtils(this,oid,uid)
        } else {
            WXPayUtil(this).WXpaySignature(uid, oid)
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun payResult(event: PayResultEvent) {
        when(event.type){
            1->{//支付成功
                val intent = Intent(this@PayActivity, PaySuccessActivity::class.java)
                intent.putExtra("money",money)
                startActivity(intent)
                finish()
            }
            2->{

            }
            3->{}
            else->{}
        }
    }
}