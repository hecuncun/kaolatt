package com.jxbn.kaolatt.bean

import com.stx.xhb.xbanner.entity.SimpleBannerInfo

/**
 * Created by hecuncun on 2019/12/8
 */
data class BannerBean(val imgUrl:String,val link:String) :SimpleBannerInfo() {
    override fun getXBannerUrl(): Any {
       return imgUrl
    }
}