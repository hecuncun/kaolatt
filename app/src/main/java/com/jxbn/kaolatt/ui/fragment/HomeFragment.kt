package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.FamousAdapter
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.adapter.GoodsListAdapter
import com.jxbn.kaolatt.bean.BannerInfoBean
import com.jxbn.kaolatt.bean.GoodListBean
import com.jxbn.kaolatt.bean.GoodsBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.ui.activity.GoodsDetailActivity
import com.jxbn.kaolatt.ui.activity.GoodsFamousActivity
import com.jxbn.kaolatt.ui.activity.GoodsListActivity
import com.jxbn.kaolatt.ui.activity.WebViewActivity
import com.jxbn.kaolatt.widget.CustomScrollView
import com.lhzw.bluetooth.base.BaseFragment
import com.orhanobut.logger.Logger
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by hecuncun on 2019/11/13
 */
class HomeFragment : BaseFragment() {



    private val goodAdapter: GoodsListAdapter by lazy {
        GoodsListAdapter()
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


        famousAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, GoodsFamousActivity::class.java)
            startActivity(intent)
        }

        moreRecommendAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity,GoodsDetailActivity::class.java)
            startActivity(intent)
        }

        tv_more.setOnClickListener {
            jumpToGoodsListActivity()
        }

        ll_web.setOnClickListener {
           // jumpToWebViewActivity()
        }


    }

    private fun jumpToWebViewActivity(url:String,type:Int) {
        val intent  =   Intent(activity, WebViewActivity::class.java)
        intent.putExtra("url",url)
        intent.putExtra("type",type)
        startActivity(intent)
    }
    private fun jumpToGoodsListActivity() {
        val intent =   Intent(activity, GoodsListActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initView(view: View) {

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

    }

  //  private var bannerList = mutableListOf<BannerBean>()
    private var bannerList = mutableListOf<BannerInfoBean.DataBean>()

    private fun initBanner() {
        val observable =  SLMRetrofit.getInstance().api.homeBannerCall()
        observable.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BannerInfoBean>(){
            override fun onSucceed(bannerInfoBean: BannerInfoBean?) {
               if(bannerInfoBean?.code == Constant.SUCCESSED_CODE){
                   bannerList = bannerInfoBean.data
                   xbanner.setBannerData(bannerList)
               }
            }

            override fun onFailed() {
            }
        })


//
//        for (i in 0..3) {
//            bannerList.add(BannerBean("https://icweiliimg6.pstatp.com/weili/l/799597196884705367.webp", "https://www.iqiyi.com/"))
//        }

        xbanner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            GlideUtils.showRound(view as ImageView, Constant.BASE_URL+(model as BannerInfoBean.DataBean).cpPicture, R.drawable.home_ban_member01, 6)
        })
        xbanner.setOnItemClickListener { banner, model, view, position ->
            showToast("点击$position")
            jumpToWebViewActivity(bannerList[position].cpContent,0)

        }

    }


    override fun lazyLoad() {
        initBanner()
        initRvData()
    }

    private var listGood = mutableListOf<GoodListBean.DataBean>()
    private val listFamous = mutableListOf<GoodsBean>()
    private val listMore = mutableListOf<GoodsBean>()
    private fun initRvData() {
        val observable = SLMRetrofit.getInstance().api.goodListCall()
        observable.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<GoodListBean>(){
            override fun onSucceed(t: GoodListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE){
                    listGood = t.data

                    goodAdapter.addData(listGood)

                }
            }

            override fun onFailed() {
            }
        })





        for (i in 0..3) {
           // listGood.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
            listFamous.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
            listMore.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
      //  goodAdapter.addData(listGood)
        famousAdapter.addData(listFamous)
        moreRecommendAdapter.addData(listMore)



    }

}