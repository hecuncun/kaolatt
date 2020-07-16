package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.OrderDetailBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/22
 */
class OrderDetailAdapter:BaseQuickAdapter<OrderDetailBean.DataBean.OrderGoodslListBean,BaseViewHolder>(R.layout.item_order_detail_list) {
    override fun convert(helper: BaseViewHolder, item: OrderDetailBean.DataBean.OrderGoodslListBean?) {
      item?:return
        helper.setText(R.id.tv_goods_name, item.name)
                .setText(R.id.tv_goods_num, "x${item.numTotal}")
                .setText(R.id.tv_money, "¥${item.priceReal}")
                .setText(R.id.tv_mark, item.specs)

        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)
        val ivList = item.picture.split(",")
        GlideUtils.showRound(ivGoods, Constant.BASE_URL+ivList[0],R.mipmap.pic_good,6)
    }
}