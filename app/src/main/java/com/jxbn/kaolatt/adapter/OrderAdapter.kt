package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.OrderListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/4
 *
 * 1->"待付款订单"
2,3->"待收货订单"
4->"待评价订单"
5->"已完成订单"
6,7->"退换货订单"
 */
class OrderAdapter : BaseQuickAdapter<OrderListBean.DataBean.RowsBean, BaseViewHolder>(R.layout.item_order_list) {
    override fun convert(helper: BaseViewHolder, item: OrderListBean.DataBean.RowsBean?) {
        item ?: return
        helper.setText(R.id.tv_order_num,"订单号：${ item.orderNo}")
                .setText(R.id.tv_goods_name, item.orderGoodslList[0].name)
                .setText(R.id.tv_goods_num, "x${item.orderGoodslList[0].numTotal}")
                .setText(R.id.tv_money, "¥${item.orderGoodslList[0].priceReal}")
                .setText(R.id.tv_goods_total, "共${item.num}件商品")
                .setText(R.id.tv_total_money, "合计：¥${item.priceTotalOrder}")
                .setText(R.id.tv_mark, "型号：${item.orderGoodslList[0].specs}")
                .setText(R.id.tv_cancel_order, if (item.status == 1) {
                    "取消订单"
                } else {
                    "查看物流"
                })
                .setText(R.id.tv_order_type,
                        when (item.status) {
                            1 -> "待付款"
                            2 -> "待发货"
                            3 -> "待收货"
                            4 -> "待评价"
                            5 -> "已完成"
                            6 -> "退货中"
                            7 -> "退货完成"
                            8 -> "取消申请中"
                            9 -> "已取消"
                            else -> ""
                        })
        val textView = helper.getView<TextView>(R.id.tv_cancel_order)
        textView.visibility = if (item.status == 1 || item.status == 2 || item.status == 3) View.VISIBLE else View.GONE
        textView.setTextColor(if (item.status == 1) mContext.resources.getColor(R.color.text_color_666666) else mContext.resources.getColor(R.color.colorPrimary))
        helper.getView<TextView>(R.id.tv_confirm_order).text = when (item.status) {
            1 -> "立即付款"
            2, 3 -> "确认收货"
            4 -> "去评价"
            5 ,9-> "删除订单"
            6, 7, 8 -> "查看详情"
            else -> ""
        }
        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)
        val ivList = item.orderGoodslList[0].picture.split(",")
        GlideUtils.showRound(ivGoods,Constant.BASE_URL+ivList[0],R.mipmap.pic_good,6)
        helper.addOnClickListener(R.id.tv_confirm_order)
        helper.addOnClickListener(R.id.tv_cancel_order)
    }


}