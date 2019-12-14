package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class PayActivity:BaseActivity() {
    private var isCheckZfb = true
    override fun attachLayoutRes(): Int = R.layout.activity_pay

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="订单支付"
        //iv_back.visibility= View.VISIBLE
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
        val intent = Intent(this@PayActivity,PaySuccessActivity::class.java)
        startActivity(intent)
    }
}