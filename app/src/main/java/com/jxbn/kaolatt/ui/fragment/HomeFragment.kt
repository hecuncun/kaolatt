package com.jxbn.kaolatt.ui.fragment

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.FamousListAdapter
import com.jxbn.kaolatt.adapter.GoodListAdapter
import com.jxbn.kaolatt.adapter.GoodsMoreAdapter
import com.jxbn.kaolatt.bean.BannerInfoBean
import com.jxbn.kaolatt.bean.FamousListBean
import com.jxbn.kaolatt.bean.GoodListBean
import com.jxbn.kaolatt.bean.GoodsMoreListBean
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
import com.stx.xhb.xbanner.XBanner
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by hecuncun on 2019/11/13
 */
class HomeFragment : BaseFragment() {

    private val goodAdapter: GoodListAdapter by lazy {
        GoodListAdapter()
    }
    private val famousAdapter: FamousListAdapter by lazy {
        FamousListAdapter()
    }
    private val moreRecommendAdapter: GoodsMoreAdapter by lazy {
        GoodsMoreAdapter()
    }

    override fun initListener() {
        scrollView.setOnScrollChangeListener(object : CustomScrollView.OnScrollChangeListener {
            override fun onScrollToStart() {
            }

            override fun onScrollToEnd() {
                currentPage++
                if (currentPage > total) {
                    return
                }
                //请求接口，显示更多
                val goodsMoreListCall = SLMRetrofit.getInstance().api.goodsMoreListCall(currentPage, 1)
                goodsMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsMoreListBean>() {
                    override fun onSucceed(t: GoodsMoreListBean?) {
                        if (t?.code == Constant.SUCCESSED_CODE) {
                            listMore.addAll(t.data.rows)
                            moreRecommendAdapter.setNewData(listMore)
                            if (currentPage == total) {
                                tv_loading_tip.text="已全部加载完成"
                            } else {
                                tv_loading_tip.text="上拉加载更多..."
                            }

                        }
                    }

                    override fun onFailed() {
                    }
                })
            }
        })


        goodAdapter.setOnItemClickListener { adapter, view, position ->
            //详情页
            val intent = Intent(activity, GoodsDetailActivity::class.java)
            intent.putExtra("gid", listGood[position].gid)
            startActivity(intent)
        }


        famousAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, GoodsFamousActivity::class.java)
            startActivity(intent)
        }

        moreRecommendAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, GoodsDetailActivity::class.java)
            startActivity(intent)
        }

        tv_more.setOnClickListener {
            jumpToGoodsListActivity()
        }

        ll_web.setOnClickListener {
            // jumpToWebViewActivity()
        }


    }

    private fun jumpToWebViewActivity(url: String, type: Int) {
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("type", type)
        startActivity(intent)
    }

    private fun jumpToGoodsListActivity() {
        val intent = Intent(activity, GoodsListActivity::class.java)
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
        rv_good.isNestedScrollingEnabled = false
        rv_famous.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = famousAdapter
        }
        rv_famous.setHasFixedSize(true)
        rv_famous.isNestedScrollingEnabled = false
        rv_more_recommend.run {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = moreRecommendAdapter
        }
        rv_more_recommend.setHasFixedSize(true)
        rv_more_recommend.isNestedScrollingEnabled = false

    }

    private var bannerList = mutableListOf<BannerInfoBean.DataBean>()

    private fun initBanner() {
        val observable = SLMRetrofit.getInstance().api.homeBannerCall()
        observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<BannerInfoBean>() {
            override fun onSucceed(bannerInfoBean: BannerInfoBean?) {
                if (bannerInfoBean?.code == Constant.SUCCESSED_CODE) {
                    bannerList = bannerInfoBean.data
                    xbanner.setBannerData(bannerList)
                }
            }

            override fun onFailed() {
            }
        })

        xbanner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            GlideUtils.showRound(view as ImageView, Constant.BASE_URL + (model as BannerInfoBean.DataBean).cpPicture, R.drawable.home_ban_member01, 6)
        })
        xbanner.setOnItemClickListener { banner, model, view, position ->
            showToast("点击$position")
            jumpToWebViewActivity(bannerList[position].cpContent, 0)

        }

    }


    override fun lazyLoad() {
        initBanner()
        initRvData()
    }

    private var listGood = mutableListOf<GoodListBean.DataBean>()
    private var listFamous = mutableListOf<FamousListBean.DataBean>()
    private var listMore = mutableListOf<GoodsMoreListBean.DataBean.RowsBean>()
    private var currentPage = 1
    private var total = 1
    private fun initRvData() {
        //好物
        val observable = SLMRetrofit.getInstance().api.goodListCall()
        observable.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodListBean>() {
            override fun onSucceed(t: GoodListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    listGood .addAll(t.data)
                    goodAdapter.setNewData(listGood)
                }
            }

            override fun onFailed() {
            }
        })

        //大牌
        val famousListCall = SLMRetrofit.getInstance().api.famousListCall()
        famousListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<FamousListBean>() {
            override fun onSucceed(t: FamousListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    listFamous.addAll(t.data)
                    famousAdapter.setNewData(listFamous)
                }

            }

            override fun onFailed() {
            }
        })

        //更多

        val goodsMoreListCall = SLMRetrofit.getInstance().api.goodsMoreListCall(currentPage, 1)
        goodsMoreListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsMoreListBean>() {
            override fun onSucceed(t: GoodsMoreListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    listMore.addAll(t.data.rows)
                    moreRecommendAdapter.setNewData(listMore)
                    total = t.data.total
                }
            }

            override fun onFailed() {
            }
        })


    }

}