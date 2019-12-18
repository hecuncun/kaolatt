package com.jxbn.kaolatt.ui.activity

import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import kotlinx.android.synthetic.main.activity_feed_back.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/4
 */
class FeedBackActivity:BaseActivity() {
    override fun attachLayoutRes(): Int = R.layout.activity_feed_back

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="意见反馈"
    }

    override fun initListener() {
        tv_confirm.setOnClickListener {
         val content = et_feedback.text.toString().trim()
            if (content.isEmpty()){
                showToast("内容不能为空")
                return@setOnClickListener
            }else{
                val feedBackCall = SLMRetrofit.getInstance().api.feedBackCall(uid, content)
                feedBackCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                    override fun onSucceed(t: BaseNoDataBean?) {
                       if (t?.code==Constant.SUCCESSED_CODE){
                           showToast("反馈成功")
                           finish()
                       }else{
                           showToast("反馈失败")
                       }
                    }

                    override fun onFailed() {
                    }
                })
            }
        }
    }
}