package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.MyBottomListDialog
import kotlinx.android.synthetic.main.activity_confirm_order.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 *
 * //添加删除线
txt1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//在代码中设置加粗
txt2.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
//添加下划线
txt3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
 * Created by heCunCun on 2019/12/10
 */
class ConfirmOrderActivity : BaseActivity() {
    private var myBottomListDialog: MyBottomListDialog? = null
    override fun attachLayoutRes(): Int = R.layout.activity_confirm_order

    override fun initData() {


    }

    override fun initView() {
        toolbar_title.text = "确认订单"
      //  iv_back.visibility = View.VISIBLE
        initBottomDialog()
    }

    val list = mutableListOf<String>()
    private fun initBottomDialog() {

        for (i in 0..3) {
            list.add("优惠券$i")
        }
        myBottomListDialog = MyBottomListDialog(this, null, list)
    }

    override fun initListener() {
     //   iv_back.setOnClickListener { finish() }
        tv_coupon.setOnClickListener {
            myBottomListDialog!!.show()
            myBottomListDialog!!.setListener { p0, p1, position, p3 ->
                showToast(list[position])
                tv_coupon.text = list[position]
                myBottomListDialog!!.dismiss()
            }
        }

        tv_pay.setOnClickListener {
            jumpToPayActivity()
        }

        rl_address.setOnClickListener {
            //新增地址
            jumpToAddressListActivity()
        }
    }

    private fun jumpToPayActivity() {
        val intent = Intent(this@ConfirmOrderActivity, PayActivity::class.java)
        startActivity(intent)

    }

    private fun jumpToAddressListActivity() {
        val intent = Intent(this@ConfirmOrderActivity, AddressListActivity::class.java)
        startActivity(intent)

    }
}