package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshOrderListEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_evaluate.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by hecuncun on 2019/12/7
 */
class EvaluateActivity :BaseActivity(){
    override fun attachLayoutRes(): Int = R.layout.activity_evaluate
    private var oid=""
    private var name=""
    private var img=""
    private var mask=""
    override fun initData() {
        oid=intent.extras.getString("oid")
        name=intent.extras.getString("name")
        img=intent.extras.getString("img")
        mask=intent.extras.getString("mask")
        tv_name.text=name
        tv_mask.text="型号:$mask"
        GlideUtils.showRound(iv_goods,Constant.BASE_URL+img,R.mipmap.pic_good,6)

    }

    override fun initView() {
        toolbar_title.text="评价"
    }

    override fun initListener() {
       tv_confirm.setOnClickListener {
           if (et_evaluate.text.toString().trim().isNotEmpty()){
               val addEvaluateCall = SLMRetrofit.getInstance().api.addEvaluateCall(uid, oid, et_evaluate.text.toString().trim())
               addEvaluateCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                   override fun onSucceed(t: BaseNoDataBean?) {
                       if (t?.code== Constant.SUCCESSED_CODE){
                           showToast("评价成功")
                           EventBus.getDefault().post(RefreshOrderListEvent())
                           finish()
                       }else{
                           showToast("评价失败:${t?.message}")
                       }

                   }

                   override fun onFailed() {
                   }
               })
           }else{
               showToast("请填写评价")
           }
       }
    }
}