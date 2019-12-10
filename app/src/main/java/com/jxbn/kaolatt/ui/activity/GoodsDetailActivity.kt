package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.ScrollView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.EvaluateAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.BannerBean
import com.jxbn.kaolatt.bean.EvaluateBean
import com.jxbn.kaolatt.bean.GoodsMaskBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.widget.GoodsDetailBottomDialog
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
    var maskDialog: MaskBottomDialog? = null
    var goodsDetailBottomDialog: GoodsDetailBottomDialog? = null
    private var bannerList = mutableListOf<BannerBean>()

    private val evaluateAdapter: EvaluateAdapter by lazy {
        EvaluateAdapter()
    }
    private val recyclerViewItemDecoration by lazy {
        SpaceItemDecoration(this)
    }

    override fun attachLayoutRes(): Int = R.layout.activity_goods_detail

    override fun initData() {
        val list = mutableListOf<EvaluateBean>()
        for (i in 0..2) {
            list.add(EvaluateBean("https://upload.jianshu.io/users/upload_avatars/9988193/fc26c109-1ae6-4327-a298-2def343e9cd8.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96/format/webp",
                    "老子说", "谢谢博主的分享！"))
        }
        evaluateAdapter.addData(list)

        //初始化弹窗数据

        for (i in 0..3) {
            mask1.add("规格$i")
            mask2.add("规格$i")
        }
        val goodsMaskBean = GoodsMaskBean("", "￥650", "销量300", mask1, mask2)
        maskDialog = MaskBottomDialog(this, goodsMaskBean)
        goodsDetailBottomDialog = GoodsDetailBottomDialog(this)
    }

    override fun initView() {
        initTab()
        initRecyclerView()
        initBanner()

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
        for (i in 0..3) {
            bannerList.add(BannerBean("https://icweiliimg6.pstatp.com/weili/l/799597196884705367.webp", "https://www.iqiyi.com/"))
        }
        xbanner.setBannerData(bannerList)
        xbanner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            GlideUtils.showRound(view as ImageView, (model as BannerBean).imgUrl, R.drawable.home_ban_member01, 6)
        })
        xbanner.setOnItemClickListener { banner, model, view, position ->
            showToast("点击$position")

        }

    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        tv_all_evaluate.setOnClickListener {
            val intent = Intent(this@GoodsDetailActivity, EvaluateListActivity::class.java)
            startActivity(intent)
        }
        tv_mask.setOnClickListener {
            maskDialog!!.show()
            maskDialog!!.setOnChoseListener(MaskBottomDialog.OnChoseListener { tab1: String?, tab2: String?, num: String? ->
                showToast("选择了 $tab1,$tab2,数量$num")
            })
        }

        tv_desc.setOnClickListener {
            goodsDetailBottomDialog!!.show()
        }

        fl_ke_fu.setOnClickListener {
            ServiceDialog.newInstance("11111111","22222222","").show(supportFragmentManager,"a")
        }
        tv_buy.setOnClickListener { //确认订单
         jumpToConfirmOrderActivity()

        }
    }

    private fun jumpToConfirmOrderActivity() {
        val intent =Intent(this@GoodsDetailActivity,ConfirmOrderActivity::class.java)
        startActivity(intent)
    }
}