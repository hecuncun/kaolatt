package com.jxbn.kaolatt.ui.activity


import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pay_success.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/10
 */
class PaySuccessActivity : BaseActivity() {
    private var oid = ""
    override fun attachLayoutRes(): Int = R.layout.activity_pay_success

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text = "支付成功"
        val money = intent.getStringExtra("money")
        oid = intent.extras.getString("oid")
        tv_money.text = "¥$money"
    }

    override fun initListener() {
        tv_finish.setOnClickListener {
           Intent(this@PaySuccessActivity,MainActivity::class.java).run {
               startActivity(this)
               finish()
           }
        }
        tv_see_detail.setOnClickListener {
            Intent(this@PaySuccessActivity,OrderDetailActivity::class.java).run {
                putExtra("oid",oid)
                startActivity(this)
                finish()
            }
        }
    }
}