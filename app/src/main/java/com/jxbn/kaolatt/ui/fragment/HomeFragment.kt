package com.jxbn.kaolatt.ui.fragment

import android.support.v4.view.ViewPager
import android.view.View
import com.jxbn.kaolatt.R
import com.lhzw.bluetooth.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*




/**
 * Created by hecuncun on 2019/11/13
 */
class HomeFragment : BaseFragment() {

    private var bannerList = mutableListOf<String>()
    override fun initListener() {
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        for (i in 0..3){
            bannerList.add("123")
        }
        initBanner()

    }

    private fun initBanner() {
        banner.addPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                //选中
            }
        })

    }

    private fun jumpToDailyStatisticsActivity() {
//        val intent = Intent(activity,DailyStatisticsActivity::class.java)
//        startActivity(intent)
    }

    override fun lazyLoad() {
    }

}