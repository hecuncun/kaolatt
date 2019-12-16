package com.jxbn.kaolatt.adapter

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.GoodListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/8
 * 首页的精品好物
 */
class GoodListAdapter:BaseQuickAdapter<GoodListBean.DataBean,BaseViewHolder>(R.layout.item_goods_list) {
    override fun convert(helper: BaseViewHolder, item: GoodListBean.DataBean?) {
        item?:return
        helper.setText(R.id.tv_name,item.name)
                .setText(R.id.tv_price,"￥${item.priceReal}")
                .setText(R.id.tv_old_price,"￥${item.priceSecond}" )
                .setText(R.id.tv_sale_num,"销量${item.salesVolume }")
        helper.getView<TextView>(R.id.tv_old_price).paint.flags= Paint.STRIKE_THRU_TEXT_FLAG
        val  imgGoods = helper.getView<ImageView>(R.id.iv_goods)
        val imgList = item.picture.split(",")
        GlideUtils.showAnimation(imgGoods,Constant.BASE_URL+imgList[0],R.mipmap.ic_launcher)
    }
}