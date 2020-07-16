package com.jxbn.kaolatt.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ScrollView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.*
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.event.RefreshCarEvent
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.GoodsInfoBottomDialog
import com.jxbn.kaolatt.widget.MaskBottomDialog
import com.jxbn.kaolatt.widget.ServiceDialog
import com.jxbn.kaolatt.widget.SpaceItemDecoration
import com.orhanobut.logger.Logger
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.greenrobot.eventbus.EventBus
import org.litepal.LitePal
import org.litepal.extension.findAll
import java.io.Serializable

/**
 * Created by heCunCun on 2019/12/9
 */
class GoodsDetailActivity : BaseActivity() {
    private var mWebView: WebView? = null

    val mask1 = mutableListOf<String>()//标签1
    val mask2 = mutableListOf<String>()//标签2
    val mask3 = mutableListOf<String>()//标签2
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
    private var orderList = mutableListOf<CartBean>()
    private var totalMoney=0.0
    private var gid :String= ""
    override fun initData() {
        gid = intent.extras.getString("gid")//商品id
        val goodsDetailCall = SLMRetrofit.getInstance().api.goodsDetailCall(gid, uid)
        goodsDetailCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsDetailBean>() {
            override fun onSucceed(t: GoodsDetailBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {

                    //轮播图
                    val imgList = t.data.picture.split(",")
                    for (url in imgList) {
                        bannerList.add(BannerBean(Constant.BASE_URL + url, ""))
                    }
                    initBanner()

                    //商品名
                    tv_name.text = t.data.name
                    tv_price.text = "¥${t.data.priceReal}"
                    tv_old_price.text = "¥${t.data.priceSecond}"
                    tv_old_price.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                    tv_sale_num.text = "销量${t.data.salesVolume}"
                   // val list:MutableList<GoodsDetailBean.DataBean.GoodsSpecsListBean.GoodsSpecsAttributeListBean>
                    var list = mutableListOf<GoodsDetailBean.DataBean.GoodsSpecsListBean.GoodsSpecsAttributeListBean>()
                    //规格弹窗  最多显示三种规格
                    val listMask = t.data.goodsSpecsList
                    var maskName1 = ""
                    var maskName2 = ""
                    var maskName3= ""
                    if (listMask.size == 1) {
                        maskName1 = listMask[0].name
                        val split = listMask[0].goodsSpecsAttributeList
                        split.forEach {
                            mask1.add(it.name)
                        }
                        list=split
                        Logger.e("mask1==>$mask1")
                    } else if (listMask.size == 2) {
                        maskName1 = listMask[0].name
                        val split1 = listMask[0].goodsSpecsAttributeList
                        split1.forEach {
                            mask1.add(it.name)
                        }

                        maskName2 = listMask[1].name
                        val split2 = listMask[1].goodsSpecsAttributeList
                        split2.forEach {
                            mask2.add(it.name)
                        }
                        list=split2
                    }else if (listMask.size>2){
                        maskName1 = listMask[0].name
                        val split1 = listMask[0].goodsSpecsAttributeList
                        split1.forEach {
                            mask1.add(it.name)
                        }

                        maskName2 = listMask[1].name
                        val split2 = listMask[1].goodsSpecsAttributeList
                        split2.forEach {
                            mask2.add(it.name)
                        }

                        maskName3 = listMask[2].name
                        val split3 = listMask[2].goodsSpecsAttributeList
                        split3.forEach {
                            mask3.add(it.name)
                        }
                        list=split3
                    }
                    var desc =""
                    if (t.data.numTotal==0 ){
                        desc="需预定"
                    }else if(t.data.numRemainder==0){
                        desc="无货"
                    }else{
                        desc="有货"
                    }
                    val goodsMaskBean = GoodsMaskBean(desc,t.data.goodsSpecsList.size,Constant.BASE_URL + imgList[0], t.data.priceReal.toString(), t.data.salesVolume, maskName1, mask1, maskName2, mask2,maskName3,mask3,list)
                    maskDialog = MaskBottomDialog(this@GoodsDetailActivity, goodsMaskBean)
                    maskDialog!!.setOnChoseListener(MaskBottomDialog.OnChoseListener { isAddCar, mask1, tab1, mask2, tab2,mask3,tab3, num,realPrice->
                       // showToast("addCar=$isAddCar,mask1=$mask1-tab1=$tab1,mask2=$mask2-tab2=$tab2,mask23=$mask3-tab3=$tab3,num=$num")
                        //todo 根据addCar 添加数据库  创建订单
                        var masks =when( t.data.goodsSpecsList.size){
                                0->{""}
                            1->{
                                "$mask1-$tab1"

                            }
                            2->{"$mask1-$tab1,$mask2-$tab2"}
                            3->{"$mask1-$tab1,$mask2-$tab2,$mask3-$tab3"}
                            else->{"$mask1-$tab1,$mask2-$tab2,$mask3-$tab3"}
                        }
                        val bean = CartBean(gid, imgList[0], t.data.name, masks,realPrice, num.toInt(), false)
                       // totalMoney=t.data.priceReal.times(num.toInt())
                        totalMoney=realPrice.toDouble().times(num.toInt())
                        if (isAddCar) {//数据库添加
                            bean.save()
                            initCarNum()
                            //刷购物车
                            EventBus.getDefault().post(RefreshCarEvent())
                        } else {//跳确认订单页
                            if (isLogin){
                                val intent = Intent(this@GoodsDetailActivity, ConfirmOrderActivity::class.java)
                                orderList.add(bean)
                                intent.putExtra("list",orderList as Serializable)
                                intent.putExtra("total",totalMoney)
                                intent.putExtra("num",num)
                                startActivity(intent)
                                orderList.clear()
                                totalMoney=0.0
                            }else{
                                val intent = Intent(this@GoodsDetailActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }

                        }

                    })
                    //产品参数
                    val listParam = t.data.goodsParamList
                    listParam.forEach {
                        paramList.add(it.name+","+it.info)
                    }
                    goodsInfoBottomDialog = GoodsInfoBottomDialog(this@GoodsDetailActivity, null, paramList)

                    val temp = t.data.content
                    val replace1 = temp.replace("src=\"http://59.110.230.192", "src=\"")
                    val replace = replace1.replace("src=\"", "src=\"${Constant.BASE_URL}")

                    //WebView详情
                    mWebView?.loadDataWithBaseURL(null, getHtmlData(replace), "text/html", "utf-8", null)
                    ll_web_container.addView(mWebView)
                   Logger.e("地址==》${t.data.content}")


                    //是否收藏
                    if (t.data.collectId == null || t.data.collectId.isEmpty()) {
                        collectType = 2
                        iv_collect.setImageResource(R.mipmap.icon_star)
                    } else {
                        iv_collect.setImageResource(R.mipmap.icon_star_pre)
                        collectType = 1
                    }
                }
            }

            override fun onFailed() {
            }
        })

        //评价

        val listEvaluate = mutableListOf<EvaluateListBean.DataBean.RowsBean>()
        val evaluateListCall = SLMRetrofit.getInstance().api.evaluateListCall(1, gid)
        evaluateListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<EvaluateListBean>() {
            override fun onSucceed(t: EvaluateListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    listEvaluate.addAll(t.data.rows)
                    tv_evaluate_num.text = "评价（${t.data.records}）"
                    evaluateAdapter.setNewData(listEvaluate)
                }
            }

            override fun onFailed() {
            }
        })


    }

    override fun initView() {
        initTab()
        initWeb()
        initRecyclerView()
        initCarNum()

    }

    private fun initCarNum() {
        val list =LitePal.findAll<CartBean>()

        tv_car_num.text = list.size.toString()

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
        xbanner.setBannerData(bannerList)
        xbanner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            GlideUtils.showRound(view as ImageView, (model as BannerBean).imgUrl, R.mipmap.pic_banner, 6)
        })


    }

    private var collectType = 2 //1收藏  2取消

    override fun initListener() {
        rl_car.setOnClickListener {
            Intent(this@GoodsDetailActivity,MainActivity::class.java).run {
                putExtra("index",0X03)
                startActivity(this)
            }
        }
        iv_back.setOnClickListener { finish() }
        tv_all_evaluate.setOnClickListener {
            val intent = Intent(this@GoodsDetailActivity, EvaluateListActivity::class.java)
            intent.putExtra("gid", gid)
            startActivity(intent)
        }

        tv_add_car.setOnClickListener {
            maskDialog!!.show()
            maskDialog!!.setShowOneBtn(true, true)
        }


        tv_mask.setOnClickListener {
            maskDialog!!.show()
            maskDialog!!.setShowOneBtn(false, false)

        }

        tv_desc.setOnClickListener {
            goodsInfoBottomDialog!!.show()
        }

        fl_ke_fu.setOnClickListener {
            ServiceDialog.newInstance("269186756", "4006633998", "").show(supportFragmentManager, "a")
        }
        tv_buy.setOnClickListener {
            //确认订单
            //jumpToConfirmOrderActivity()
            maskDialog!!.show()
            maskDialog!!.setShowOneBtn(true, false)

        }
        iv_collect.setOnClickListener {
            if (collectType == 1) {//已收藏
                collectType = 2
            } else {
                collectType = 1
            }
            if (isLogin){
                val collectionEnsureCall = SLMRetrofit.getInstance().api.collectionEnsureCall(gid, uid, collectType)
                collectionEnsureCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BaseNoDataBean>() {
                    override fun onSucceed(t: BaseNoDataBean?) {
                        if (t?.code == Constant.SUCCESSED_CODE) {
                            if (collectType == 1) {
                                showToast("收藏成功")
                                iv_collect.setImageResource(R.mipmap.icon_star_pre)
                            } else {
                                showToast("取消收藏")
                                iv_collect.setImageResource(R.mipmap.icon_star)
                            }
                        }
                    }

                    override fun onFailed() {

                    }
                })
            }else{
                Intent(this,LoginActivity::class.java).run {
                    startActivity(this)
                }
            }

        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWeb() {
        mWebView = WebView(this@GoodsDetailActivity)
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