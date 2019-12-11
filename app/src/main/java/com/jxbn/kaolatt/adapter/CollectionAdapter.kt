package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CollectionBean
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionAdapter : BaseQuickAdapter<CollectionBean, BaseViewHolder>(R.layout.item_collection_list) {
    private var show = false
    override fun convert(helper: BaseViewHolder, item: CollectionBean?) {
        item ?: return
        helper.setText(R.id.tv_goods_name, item.goodsName)
                .setText(R.id.tv_goods_price, item.price)
                .setText(R.id.tv_sale_num, "销量${item.saleNum.toString()}")
        val ivCheck = helper.getView<ImageView>(R.id.iv_check)
        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)
        GlideUtils.showRound(ivGoods,"https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg",R.mipmap.ic_launcher,8)
        ivCheck.setImageResource(if(item.isChecked) R.mipmap.icon_check_pre else  R.mipmap.icon_check)
        helper.addOnClickListener(R.id.iv_check)
        if (show) {
            ivCheck.visibility = View.VISIBLE
        } else {
            ivCheck.visibility = View.GONE
        }

    }

    fun setShowCheckIcon(showCheckIcon: Boolean) {
        show = showCheckIcon
        notifyDataSetChanged()
    }

}