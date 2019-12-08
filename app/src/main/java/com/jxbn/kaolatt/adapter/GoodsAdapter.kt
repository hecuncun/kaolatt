package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.GoodsBean
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/8
 */
class GoodsAdapter:BaseQuickAdapter<GoodsBean,BaseViewHolder>(R.layout.item_goods_list) {
    override fun convert(helper: BaseViewHolder, item: GoodsBean?) {
        item?:return
        helper.setText(R.id.tv_name,item.name)
                .setText(R.id.tv_price,"￥${item.price.toString()}")
                .setText(R.id.tv_old_price,item.oldPrice)
                .setText(R.id.tv_sale_num,"销量${item.saleNum}")
        val  imgGoods = helper.getView<ImageView>(R.id.iv_goods)
        GlideUtils.showAnimation(imgGoods,item.imgUrl,R.mipmap.logo)

    }
}