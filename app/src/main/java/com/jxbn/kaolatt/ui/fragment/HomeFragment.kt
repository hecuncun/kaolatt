package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.FamousAdapter
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.bean.BannerBean
import com.jxbn.kaolatt.bean.GoodsBean
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.ui.activity.GoodsDetailActivity
import com.jxbn.kaolatt.widget.CustomScrollView
import com.lhzw.bluetooth.base.BaseFragment
import com.orhanobut.logger.Logger
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by hecuncun on 2019/11/13
 */
class HomeFragment : BaseFragment() {

    private var bannerList = mutableListOf<BannerBean>()

    private val goodAdapter: GoodsAdapter by lazy {
        GoodsAdapter()
    }
    private val famousAdapter: FamousAdapter by lazy {
        FamousAdapter()
    }
    private val moreRecommendAdapter: GoodsAdapter by lazy {
        GoodsAdapter()
    }

    override fun initListener() {
        scrollView.setOnScrollChangeListener(object :CustomScrollView.OnScrollChangeListener{
            override fun onScrollToStart() {
            }

            override fun onScrollToEnd() {

            //请求接口，显示更多
            for(i in 0..3){
                listMore.add(GoodsBean("SK2小黑瓶安瓶精华",500f,"￥800","200","https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
            }
            moreRecommendAdapter.setNewData(listMore)
                Logger.e("OnLoadMoreListener==${listMore.size}")
            moreRecommendAdapter.loadMoreEnd()
            }
        })


        goodAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity,GoodsDetailActivity::class.java)
            startActivity(intent)

        }
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        initBanner()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_good.run {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = goodAdapter
        }
        rv_good.setHasFixedSize(true)
        rv_good.isNestedScrollingEnabled=false
        rv_famous.run {
            layoutManager =LinearLayoutManager(activity)
            adapter = famousAdapter
        }
        rv_famous.setHasFixedSize(true)
        rv_famous.isNestedScrollingEnabled=false
        rv_more_recommend.run {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = moreRecommendAdapter
        }
        rv_more_recommend.setHasFixedSize(true)
        rv_more_recommend.isNestedScrollingEnabled=false

//        moreRecommendAdapter.setOnLoadMoreListener({
//            val list3 = mutableSetOf<GoodsBean>()
//            Logger.e("OnLoadMoreListener==${listMore.size}")
//            //请求接口，显示更多
//            for(i in 0..3){
//                list3.add(GoodsBean("SK2小黑瓶安瓶精华",500f,"￥800","200","https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
//            }
//            moreRecommendAdapter.addData(list3)
//            moreRecommendAdapter.loadMoreComplete()
//    },rv_famous)
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

    private fun jumpToDailyStatisticsActivity() {
//        val intent = Intent(activity,DailyStatisticsActivity::class.java)
//        startActivity(intent)
    }

    override fun lazyLoad() {
        initRvData()
    }

    private val list = mutableListOf<GoodsBean>()
    private val listMore = mutableListOf<GoodsBean>()
    private fun initRvData() {
        for (i in 0..3) {
            list.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
            listMore.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
        goodAdapter.addData(list)
        famousAdapter.addData(list)
        moreRecommendAdapter.addData(listMore)



    }

}