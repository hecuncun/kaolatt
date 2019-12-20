package com.jxbn.kaolatt.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.ScrollView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.BannerBean
import com.jxbn.kaolatt.bean.EvaluateListBean
import com.jxbn.kaolatt.bean.GoodsDetailBean
import com.jxbn.kaolatt.bean.GoodsMaskBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.GoodsInfoBottomDialog
import com.jxbn.kaolatt.widget.MaskBottomDialog
import com.jxbn.kaolatt.widget.ServiceDialog
import com.jxbn.kaolatt.widget.SpaceItemDecoration
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Created by heCunCun on 2019/12/9
 */
class GoodsDetailActivity : BaseActivity() {
    val mask1 = mutableListOf<String>()//标签1
    val mask2 = mutableListOf<String>()//标签2
    val paramList = mutableListOf<String>()//标签2
    var maskDialog: MaskBottomDialog? = null
    var goodsInfoBottomDialog: GoodsInfoBottomDialog? = null
    private var bannerList = mutableListOf<BannerBean>()

    private val evaluateAdapter: EvaluateAdapter by lazy {
        EvaluateAdapter()
    }
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }

    override fun attachLayoutRes(): Int = R.layout.activity_goods_detail
    private var gid = ""
    override fun initData() {
        gid = intent.extras.getString("gid")//商品id
        val goodsDetailCall = SLMRetrofit.getInstance().api.goodsDetailCall(gid)
        goodsDetailCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsDetailBean>() {
            override fun onSucceed(t: GoodsDetailBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {

                    //轮播图
                    val imgList = t.data.picture.split(",")
                    for (url in imgList) {
                        bannerList.add(BannerBean(Constant.BASE_URL+url, ""))
                    }
                    initBanner()

                    //商品名
                    tv_name.text = t.data.name
                    tv_price.text = "￥${t.data.priceReal}"
                    tv_old_price.text = "￥${t.data.priceSecond}"
                    tv_old_price.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                    tv_sale_num.text = "销量${t.data.salesVolume}"

                    //规格弹窗  最多显示两种规格
                    val listMask = t.data.goodsSpecslList
                    var maskName1 = ""
                    var maskName2 = ""
                    if (listMask.size == 1) {
                        maskName1 = listMask[0].name
                        val split = listMask[0].info.split(",")
                        split.forEach {
                            mask1.add(it)
                        }
                    } else if (listMask.size == 2) {
                        maskName1 = listMask[0].name
                        val split1 = listMask[0].info.split(",")
                        split1.forEach {
                            mask1.add(it)
                        }

                        maskName2 = listMask[1].name
                        val split2 = listMask[1].info.split(",")
                        split2.forEach {
                            mask2.add(it)
                        }

                    }
                    val goodsMaskBean = GoodsMaskBean(Constant.BASE_URL + imgList[0], t.data.priceReal.toString(), t.data.salesVolume, maskName1, mask1, maskName2, mask2)
                    maskDialog = MaskBottomDialog(this@GoodsDetailActivity, goodsMaskBean)

                    //产品参数
                     val listParam= t.data.goodsParamList
                    listParam.forEach {
                        paramList.add(it.name+"                               "+it.info)
                    }
                    goodsInfoBottomDialog= GoodsInfoBottomDialog(this@GoodsDetailActivity,null,paramList)

                    //goodsDetailBottomDialog = GoodsDetailBottomDialog(this@GoodsDetailActivity)

                    //WebView详情
                    webView.loadDataWithBaseURL(null,getHtmlData(t.data.content), "text/html" , "utf-8", null)

                    //是否收藏
                    if (t.data.collectId==null || t.data.collectId.isEmpty()){
                        collectType=2
                        iv_collect.setImageResource(R.mipmap.icon_star)
                    }else{
                        iv_collect.setImageResource(R.mipmap.icon_star_pre)
                        collectType=1
                    }
                }
            }

            override fun onFailed() {
            }
        })

        //评价

        val listEvaluate = mutableListOf<EvaluateListBean.DataBean.RowsBean>()
        val evaluateListCall = SLMRetrofit.getInstance().api.evaluateListCall(1, gid)
        evaluateListCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<EvaluateListBean>(){
            override fun onSucceed(t: EvaluateListBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    listEvaluate.addAll(t.data.rows)
                    tv_evaluate_num.text="评价（${t.data.records}）"
                    evaluateAdapter.setNewData(listEvaluate)
                }
            }

            override fun onFailed() {
            }
        })



//        for (i in 0..2) {
//            list.add(EvaluateBean("https://upload.jianshu.io/users/upload_avatars/9988193/fc26c109-1ae6-4327-a298-2def343e9cd8.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp",
//                    "老子说", "谢谢博主的分享！"))
//        }
//        evaluateAdapter.addData(list)


    }

    override fun initView() {
        initTab()
        initWeb()
        initRecyclerView()
        // initBanner()

    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@GoodsDetailActivity)
            adapter = evaluateAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(recyclerViewItemDecoration)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

    }

    /**
     * 顶部选择器
     */
    private fun initTab() {
        tab_layout.addTab(tab_layout.newTab().setText("商品"))
        tab_layout.addTab(tab_layout.newTab().setText("评价"))
        tab_layout.addTab(tab_layout.newTab().setText("详情"))

        tab_layout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                //选中tab\
                showToast(tab?.text.toString())
                when (tab?.text) {
                    "商品" -> {
                        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_UP) }
                    }
                    "评价" -> {
                        scrollView.post { scrollView.scrollTo(0, ll_evaluate_container.top) }
                    }
                    "详情" -> {
                        scrollView.post { scrollView.scrollTo(0, ll_detail_container.top) }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    private fun initBanner() {
//        for (i in 0..3) {
//            bannerList.add(BannerBean("https://icweiliimg6.pstatp.com/weili/l/799597196884705367.webp", "https://www.iqiyi.com/"))
//        }
        xbanner.setBannerData(bannerList)
        xbanner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            GlideUtils.showRound(view as ImageView, (model as BannerBean).imgUrl, R.drawable.home_ban_member01, 6)
        })
//        xbanner.setOnItemClickListener { banner, model, view, position ->
//            showToast("点击$position")
//
//        }

    }

    private var collectType =2 //1收藏  2取消

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        tv_all_evaluate.setOnClickListener {
            val intent = Intent(this@GoodsDetailActivity, EvaluateListActivity::class.java)
            intent.putExtra("gid",gid)
            startActivity(intent)
        }
        tv_mask.setOnClickListener {
            maskDialog!!.show()
            maskDialog!!.setOnChoseListener(MaskBottomDialog.OnChoseListener { tab1: String?, tab2: String?, num: String? ->
                showToast("选择了 $tab1,$tab2,数量$num")
            })
        }

        tv_desc.setOnClickListener {
            goodsInfoBottomDialog!!.show()
        }

        fl_ke_fu.setOnClickListener {
            ServiceDialog.newInstance("11111111", "22222222", "").show(supportFragmentManager, "a")
        }
        tv_buy.setOnClickListener {
            //确认订单
            jumpToConfirmOrderActivity()

        }
        iv_collect.setOnClickListener {
            if (collectType==1){//已收藏
                collectType=2
            }else{
                collectType=1
            }
            val collectionEnsureCall = SLMRetrofit.getInstance().api.collectionEnsureCall(gid, uid, collectType)
            collectionEnsureCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                override fun onSucceed(t: BaseNoDataBean?) {
                    if(t?.code==Constant.SUCCESSED_CODE){
                        if (collectType==1){
                            showToast("收藏成功")
                            iv_collect.setImageResource(R.mipmap.icon_star_pre)
                        }else{
                            showToast("取消收藏")
                            iv_collect.setImageResource(R.mipmap.icon_star)
                        }
                    }
                }

                override fun onFailed() {

                }
            })
        }
    }

    private fun jumpToConfirmOrderActivity() {
        val intent = Intent(this@GoodsDetailActivity, ConfirmOrderActivity::class.java)
        startActivity(intent)
    }


    private fun initWeb() {
        val webSettings = webView.settings
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

    override fun onDestroy() {
        webView.removeAllViews()
        webView.destroy()
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
}