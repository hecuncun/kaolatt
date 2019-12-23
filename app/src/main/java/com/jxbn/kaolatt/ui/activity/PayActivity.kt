package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.PaySignBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.pay.WXPayUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class PayActivity:BaseActivity() {
    private var oid=""
    private var money=""
    private var isCheckZfb = true
    override fun attachLayoutRes(): Int = R.layout.activity_pay

    override fun initData() {



    }

    override fun initView() {
        toolbar_title.text="订单支付"
        //iv_back.visibility= View.VISIBLE
        oid  =  intent.getStringExtra("oid")//订单id
        money  =  intent.getStringExtra("money")//订单id
        Logger.e("oid==$oid")
        tv_pay_money.text="待支付金额：¥$money"
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
            //支付成功 跳支付成功页
            jumpToPaySuccessActivity()
        }
    }

    private fun jumpToPaySuccessActivity() {
        val paySignCall = SLMRetrofit.getInstance().api.paySignCall(uid, oid, if (isCheckZfb) 2 else 1)
        paySignCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<PaySignBean>(){
            override fun onSucceed(t: PaySignBean?) {
               if (t?.code==Constant.SUCCESSED_CODE){
//                   "timeStamp": "1577117175",
//                   "paySign": "F002A67C2F82948EB93017B856E62F92",
//                   "signType": "MD5",
//                   "prepayId": "wx240006154789537020e7b2831107043800",
//                   "nonceStr": "1577117175"
                   t.data.nonceStr
               }
            }

            override fun onFailed() {
            }
        })



        if (isCheckZfb){

        }else{
            WXPayUtil(this).WXpaySignature(uid,oid)
        }



        val intent = Intent(this@PayActivity,PaySuccessActivity::class.java)
        startActivity(intent)
    }
}