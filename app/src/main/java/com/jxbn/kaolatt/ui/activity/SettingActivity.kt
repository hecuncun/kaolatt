package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.event.LoginEvent
import com.jxbn.kaolatt.ext.startActivityCheckLogin
import com.jxbn.kaolatt.utils.Preference
import com.jxbn.kaolatt.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by heCunCun on 2019/12/4
 */
class SettingActivity:BaseActivity() {
    private  var dialog:LogoutDialog?=null
    override fun attachLayoutRes(): Int = R.layout.activity_setting

    override fun initData() {
    }

    override fun initView() {
        toolbar_title.text="设置"
         dialog = LogoutDialog(this)



    }

    override fun initListener() {
        rl_about_us.setOnClickListener { jumpToAboutUsActivity() }
        rl_feedback.setOnClickListener { jumpToFeedbackActivity() }
        rl_change_pwd.setOnClickListener{ jumpToChangePwdActivity()}
        rl_logout.setOnClickListener {
            dialog!!.show()
            dialog!!.setConfirmListener(View.OnClickListener {
                dialog!!.dismiss()
               // showToast("确定")
                Preference.clearPreference()
                EventBus.getDefault().post(LoginEvent(false))
                finish()
            })

        }
    }

    private fun jumpToAboutUsActivity() {
        val intent = Intent(this@SettingActivity,AboutUsActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToFeedbackActivity() {
        startActivityCheckLogin(FeedBackActivity::class.java)
    }
    private fun jumpToChangePwdActivity() {
        startActivityCheckLogin(ChangePwdActivity::class.java)
    }
}