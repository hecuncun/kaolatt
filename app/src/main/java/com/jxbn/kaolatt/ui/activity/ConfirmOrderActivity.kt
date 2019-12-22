package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.AddressListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.SetDefaultAddressEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.MyBottomListDialog
import kotlinx.android.synthetic.main.activity_confirm_order.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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

    private var addressId: String? = null

    override fun initData() {
        //查默认地址
        updateAddressInfo()
        tv_score.text="当前可用${score}积分"
    }

    private fun updateAddressInfo() {
        val addressListCall = SLMRetrofit.getInstance().api.addressListCall(uid)
        addressListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<AddressListBean>() {
            override fun onSucceed(t: AddressListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    val bean = t.data.find {
                        it.remark4 == "1"
                    }
                    addressId = bean?.magorid
                    tv_name.text = "收货人:${bean?.name}"
                    tv_phone.text = "${bean?.phone}"
                    tv_address.text = "收货地址 :${bean?.area + bean?.areaDetail}"

                }
            }

            override fun onFailed() {

            }
        })
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

    private var integralNum=0//使用积分数量

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

        et_score.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(et_score.text.toString().trim().isNotEmpty()){
                    var integralNum = et_score.text.toString().trim().toInt()
                    if (integralNum>score){
                        showToast("超出可用积分")
                        integralNum=score
                        et_score.setText(score.toString())
                    }
                    tv_money_score.text="已减${integralNum.div(100.00)}元"
                }else{
                    tv_money_score.text="已减0元"
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun jumpToPayActivity() {
        val intent = Intent(this@ConfirmOrderActivity, PayActivity::class.java)
        startActivity(intent)

    }

    private fun jumpToAddressListActivity() {
        val intent = Intent(this@ConfirmOrderActivity, AddressListActivity::class.java)
        startActivity(intent)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateAddress(event:SetDefaultAddressEvent) {

        updateAddressInfo()

    }
}