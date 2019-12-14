package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.widget.LogoutDialog
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar.*

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
       // iv_back.visibility= View.VISIBLE
         dialog = LogoutDialog(this)



    }

    override fun initListener() {
       // iv_back.setOnClickListener { finish() }
        rl_about_us.setOnClickListener { jumpToAboutUsActivity() }
        rl_feedback.setOnClickListener { jumpToFeedbackActivity() }
        rl_change_pwd.setOnClickListener{ jumpToChangePwdActivity()}
        rl_logout.setOnClickListener {
            dialog!!.show()
            dialog!!.setConfirmListener(View.OnClickListener {
                dialog!!.dismiss()
                showToast("确定")
            })

        }
    }

    private fun jumpToAboutUsActivity() {
        val intent = Intent(this@SettingActivity,AboutUsActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToFeedbackActivity() {
        val intent = Intent(this@SettingActivity,FeedBackActivity::class.java)
        startActivity(intent)
    }
    private fun jumpToChangePwdActivity() {
        val intent = Intent(this@SettingActivity,ChangePwdActivity::class.java)
        startActivity(intent)
    }
}