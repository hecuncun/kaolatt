package com.jxbn.kaolatt.ui.activity

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by hecuncun on 2019/12/14
 */
class WebViewActivity :BaseActivity() {
    override fun attachLayoutRes(): Int= R.layout.activity_webview
    override fun initData() {

    }

    override fun initView() {
        toolbar_title.text="网页详情"
        initWeb()
        setUrl(0)
    }

    private fun setUrl(type:Int) {
        webView.post {
           when(type){
               0->webView.loadUrl("https://market.m.taobao.com/app/tb-source-app/aiguangjiepc/item/index.html?spm=a21bo.2017.2001.6.5af911d9qSt8sr&itemId=200404905502")
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
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            webView.clearHistory()
            (webView.parent as ViewGroup).removeView(webView)
            webView.destroy()
        }
    }
}