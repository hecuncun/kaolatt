package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.OrderGoodsListAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.*
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.SetDefaultAddressEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.utils.DateUtils
import com.jxbn.kaolatt.utils.ToJsonUtil
import com.jxbn.kaolatt.widget.MyBottomListDialog
import com.orhanobut.logger.Logger
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
    override fun useEventBus() = true
    private var myBottomListDialog: MyBottomListDialog? = null
    override fun attachLayoutRes(): Int = R.layout.activity_confirm_order

    private val mAdapter: OrderGoodsListAdapter by lazy {
        OrderGoodsListAdapter()
    }

    private var addressId: String? = null
    private var orderList = mutableListOf<CartBean>()
    private var totalMoney = 0.00
    private var numTotal = "0"
    private var couponList= mutableListOf<CouponListBean.DataBean>()
    override fun initData() {
        //获取订单商品列表
        orderList = intent.getSerializableExtra("list") as MutableList<CartBean>
        mAdapter.setNewData(orderList)
        Logger.e("orderList.size==${orderList.size}")

        totalMoney = intent.getDoubleExtra("total", 0.00)
        numTotal = intent.getStringExtra("num")

        tv_score.text = "当前可用${score}积分"
        tv_total.text = "¥$totalMoney"
        tv_num_total.text = "共${numTotal}件商品"
        tv_bottom_num.text="共${numTotal}件"

        //查默认地址
        updateAddressInfo()

        //获取优惠券
        val couponListCall = SLMRetrofit.getInstance().api.couponListCall(uid, 2)
        couponListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<CouponListBean>(){
            override fun onSucceed(t: CouponListBean?) {
                if (t?.code== Constant.SUCCESSED_CODE){
                    couponList = t.data
                    couponList.forEach {
                        list.add("满${it.valueMax}减${it.valueSubtraction}优惠券" )
                    }
                    myBottomListDialog = MyBottomListDialog(this@ConfirmOrderActivity, null, list)
                }
            }

            override fun onFailed() {
            }
        })

    }

    private fun updateAddressInfo() {
        val addressListCall = SLMRetrofit.getInstance().api.addressListCall(uid)
        addressListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<AddressListBean>() {
            override fun onSucceed(t: AddressListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    val bean = t.data.find {
                        it.remark4 == "1"
                    }
                    if (bean != null) {
                        addressId = bean.magorid
                        tv_name.text = "收货人:${bean.name}"
                        tv_phone.text = "${bean.phone}"
                        tv_address.text = "收货地址 :${bean.area + bean.areaDetail}"
                    } else {
                        addressId = ""
                        tv_name.text = "收货人 : 待添加"
                        tv_phone.text = "手机号 : 待添加"
                        tv_address.text = "收货地址 :待添加"
                    }


                }
            }

            override fun onFailed() {

            }
        })
    }

    override fun initView() {
        toolbar_title.text = "确认订单"
        //  iv_back.visibility = View.VISIBLE

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@ConfirmOrderActivity)
            adapter = mAdapter
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
    }

    val list = mutableListOf<String>()


    private var integralNum = 0//使用积分数量

    private var couponId:String?=null//优惠券id
    override fun initListener() {
        //   iv_back.setOnClickListener { finish() }
        tv_coupon.setOnClickListener {
            if (list.isEmpty()){
                showToast("暂无优惠券")
                return@setOnClickListener
            }
            myBottomListDialog?.show()
            myBottomListDialog?.setListener { p0, p1, position, p3 ->
                showToast(list[position])
                if (couponList[position].valueMax>totalMoney){
                    showToast("该优惠券未达到使用门槛")
                }else if(System.currentTimeMillis()- DateUtils.stringToLong(couponList[position].endTime,"yyyy-MM-dd HH:mm:ss")<0){
                    showToast("该优惠券已过期")
                }else{
                    couponId = couponList[position].sid
                    tv_coupon.text = list[position]
                    myBottomListDialog?.dismiss()
                }

            }
        }
      //todo 先下单  下单成功进入支付页
        tv_pay.setOnClickListener {
            //处理orderList 转化为json  OrderGoodsBean
            val list = mutableListOf<OrderGoodsBean>()
            orderList.forEach {
                list.add(OrderGoodsBean(it.goodsId,it.num.toString(),it.mask))
            }
            val json = ToJsonUtil.getInstance().toJson(list)
            Logger.e(json)
            val addOrderCall = SLMRetrofit.getInstance().api.addOrderCall(uid, addressId, couponId, integralNum, json)
            addOrderCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<AddOrderBean>(){
                override fun onSucceed(t: AddOrderBean?) {
                    when(t?.code){
                        Constant.SUCCESSED_CODE->{
                            Logger.e("oid==${t.data.oid}")
                            showToast("创建成功")
                            val intent = Intent(this@ConfirmOrderActivity, PayActivity::class.java)
                            intent.putExtra("oid",t.data.oid)
                            intent.putExtra("money",t.data.priceTotalOrder.toString())
                            startActivity(intent)
                        //   jumpToPayActivity()
                        }
                        "10002"-> showToast("失败")
                        "10003"-> showToast("系统异常")
                        "41001"-> showToast("商品数量不足")
                        "41003"-> showToast("该优惠券已使用")
                        "41004"-> showToast("该优惠券未到使用时间")
                        "41005"-> showToast("该优惠券已失效")
                        "41006"-> showToast("订单金额小于优惠券阈值，不允许使用优惠券")
                        else-> showToast("失败")
                    }

                }

                override fun onFailed() {
                }
            })

        }

        rl_address.setOnClickListener {
            //新增地址
            jumpToAddressListActivity()
        }

        et_score.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_score.text.toString().trim().isNotEmpty()) {
                    var integralNum = et_score.text.toString().trim().toInt()
                    if (integralNum > score) {
                        showToast("超出可用积分")
                        integralNum = score
                        et_score.setText(score.toString())
                    }
                    tv_money_score.text = "已减${integralNum.div(100.00)}元"
                } else {
                    tv_money_score.text = "已减0元"
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
    fun updateAddress(event: SetDefaultAddressEvent) {
        updateAddressInfo()
    }
}