package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CartBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by heCunCun on 2019/12/23
 */
class OrderGoodsListAdapter:BaseQuickAdapter<CartBean,BaseViewHolder>(R.layout.item_order_goods_list) {
    override fun convert(helper: BaseViewHolder, item: CartBean?) {
        item?:return
        helper.setText(R.id.tv_goods_name,item.goodsName)
        helper.setText(R.id.tv_mark,"型号:${item.mask}")
        helper.setText(R.id.tv_goods_num,"x${item.num}")
        helper.setText(R.id.tv_money,"¥${item.price}")
        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)
        GlideUtils.showRound(ivGoods,Constant.BASE_URL+item.imgUrl,R.mipmap.pic_good,6)

    }
}