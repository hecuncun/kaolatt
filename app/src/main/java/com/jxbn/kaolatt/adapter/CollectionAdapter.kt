package com.jxbn.kaolatt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CollectionBean

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionAdapter :BaseQuickAdapter<CollectionBean,BaseViewHolder>(R.layout.item_collection_list) {
    override fun convert(helper: BaseViewHolder, item: CollectionBean?) {
      item?:return
        helper.setText(R.id.tv_goods_name,item.goodsName)
                .setText(R.id.tv_goods_price,item.price)
                .setText(R.id.tv_sale_num,"销量${item.saleNum.toString()}")


    }
}