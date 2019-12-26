package com.jxbn.kaolatt.ui.activity

import android.annotation.SuppressLint
import android.os.Build
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by hecuncun on 2019/12/14
 *
 * type: 0 首页轮播图详情  1 消息详情   2物流   3 购物须知  4 会员特权  5企业宣传 6活动指南
 */
class WebViewActivity :BaseActivity() {
    private var type = 0
    private var url =""
    private var mWebView: WebView? = null
    override fun attachLayoutRes(): Int= R.layout.activity_webview
    override fun initData() {
//        val bundle = intent.extras
//        if (bundle.containsKey("url")) {
//            val data = bundle.getString("url")
//
//        }
//        if (bundle.containsKey("title")) {
//            val title = bundle.getString("title")
//            toolbar_title.text=title
//        }
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
        mWebView=findViewById(R.id.webView)
        type = intent.extras.getInt("type")
        url = intent.extras.getString("url")
        when(type){//3 购物须知  4 会员特权  5企业宣传 6活动指南
            0-> toolbar_title.text="活动详情"
            1-> toolbar_title.text="消息内容"
            2-> toolbar_title.text="物流信息"
            3-> toolbar_title.text="购物须知"
            4-> toolbar_title.text="会员特权"
            5-> toolbar_title.text="企业宣传"
            6-> toolbar_title.text="活动指南"
        }
        initWeb()
        setUrl(type)
    }

    private fun setUrl(type:Int) {
        mWebView?.post {
           when(type){
               0,1,3,4,5,6->mWebView?.loadDataWithBaseURL(null,getHtmlData(url), "text/html" , "utf-8", null)
               2->{}
               else->{}
           }
        }


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb() {
        val settings = mWebView?.settings
        settings?.defaultTextEncodingName = "utf-8"
        settings?.javaScriptEnabled = true
        settings?.setSupportZoom(true)
        settings?.builtInZoomControls = true
        settings?.useWideViewPort = true
        settings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings?.loadWithOverviewMode = true
        //隐藏缩放控件
        settings?.displayZoomControls = false
        //解决HTTPS协议下出现的mixed content问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        settings?.cacheMode = WebSettings.LOAD_DEFAULT
        settings?.domStorageEnabled = true
        settings?.databaseEnabled = true
        settings?.setAppCachePath(cacheDir.path)
        settings?.setAppCacheEnabled(true)
    }

    override fun initListener() {

    }

    override fun onPause() {
        super.onPause()
        mWebView?.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        mWebView?.resumeTimers()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (mWebView?.canGoBack()!!) {
            mWebView?.goBack()
        } else {
            finish()
        }
        return true
    }
//
//    companion object {
//        fun start(context: Context, url: String, title: String) {
//            val bundle = Bundle()
//            val intent = Intent(context, WebViewActivity::class.java)
//            bundle.putString("url", url)
//            bundle.putString("title", title)
//            intent.putExtras(bundle)
//            context.startActivity(intent)
//        }
//    }
}