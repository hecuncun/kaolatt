package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.MyCollectionListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionAdapter : BaseQuickAdapter<MyCollectionListBean.DataBean, BaseViewHolder>(R.layout.item_collection_list) {
    private var show = false
    override fun convert(helper: BaseViewHolder, item: MyCollectionListBean.DataBean?) {
        item ?: return
        helper.setText(R.id.tv_goods_name, item.name)
                .setText(R.id.tv_goods_price, "¥${item.priceReal}" )
                .setText(R.id.tv_sale_num, "销量${item.salesVolume }")
        val ivCheck = helper.getView<ImageView>(R.id.iv_check)
        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)
        val imgList = item.picture.split(",")
        GlideUtils.showRound(ivGoods,Constant.BASE_URL+imgList[0],R.mipmap.ic_launcher,8)
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