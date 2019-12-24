package com.jxbn.kaolatt.ui.activity

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.R.id.webView
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by hecuncun on 2019/12/14
 *
 * type: 0 首页轮播图详情  1 消息详情  2物流
 */
class WebViewActivity :BaseActivity() {
    private var type = 0
    private var url =""
    override fun attachLayoutRes(): Int= R.layout.activity_webview
    override fun initData() {

    }

    /**
     * 富文本的样式做到适配屏幕
     */
    private fun getHtmlData(bodyHTML: String): String {
        val head = ("<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:100%; height:auto;}*{margin:0px;}</style>"
                + "</head>")
        return "<html>$head<body>$bodyHTML</body></html>"
    }

    override fun initView() {
        type = intent.extras.getInt("type")
        url = intent.extras.getString("url")
        when(type){
            0-> toolbar_title.text="活动详情"
            1-> toolbar_title.text="消息内容"
            2-> toolbar_title.text="物流信息"
        }
        initWeb()
        setUrl(type)
    }

    private fun setUrl(type:Int) {
        webView.post {
           when(type){
               0,1->webView.loadDataWithBaseURL(null,getHtmlData(url), "text/html" , "utf-8", null)
               2->{}
               else->{}
           }
        }


    }

    private fun initWeb() {
        val webSettings = webView.settings
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        int scale = dm.densityDpi;
//        if (scale == 240) { //设置自动适配
//            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        } else if (scale == 160) {
//            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
//        } else {
//            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
//        }

        webSettings.setAllowFileAccess(true)
        webSettings.setDatabaseEnabled(true)
        val dir = applicationContext
                .getDir("database", Context.MODE_PRIVATE).path
        webSettings.setDatabasePath(dir)
        webSettings.setDomStorageEnabled(true)
        webSettings.setGeolocationEnabled(true)
        webSettings.setJavaScriptEnabled(true)
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(false)
        webSettings.setUseWideViewPort(true)
        webSettings.setLoadWithOverviewMode(true)
        webSettings.setSupportZoom(true)
        //设置webView的背景色
        webView.setBackgroundColor(0)
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE)
        webSettings.setPluginState(WebSettings.PluginState.ON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        }
        //去掉长按复制功能
        webView.setOnLongClickListener(View.OnLongClickListener { true })
    }

    override fun initListener() {

    }

    override fun onDestroy() {
        webView.removeAllViews()
        webView.destroy()
        EventBus.getDefault().unregister(this)
        destroyWebView()
        super.onDestroy()
    }

    /**
     * 销毁webview
     */
    private fun destroyWebView() {
        if (webView != null) {
            webView.settings.setJavaScriptEnabled(false)
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            webView.clearHistory()
            (webView.parent as ViewGroup).removeView(webView)
            webView.destroy()
        }
    }
}