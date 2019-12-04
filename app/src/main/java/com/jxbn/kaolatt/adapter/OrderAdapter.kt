package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.OrderBean

/**
 * Created by hecuncun on 2019/12/4
 */
class OrderAdapter:BaseQuickAdapter<OrderBean,BaseViewHolder>(R.layout.item_order_list){
    override fun convert(helper: BaseViewHolder, item: OrderBean?) {
       item?:return
        helper.setText(R.id.tv_order_num,item.orderNum)
                .setText(R.id.tv_goods_name,item.goodsName)
                .setText(R.id.tv_goods_num,item.goodsNum.toString())
                .setText(R.id.tv_money,item.money.toString())
                .setText(R.id.tv_mark,item.mark)
                .setText(R.id.tv_order_type,
                        when(item.type){
                            0->"待付款"
                            1->"待收货"
                            2->"待评价"
                            3->"已完成"
                            4->"退货中"
                            else ->""})
        helper.getView<TextView>(R.id.tv_cancel_order).visibility=if(item.type==0) View.VISIBLE else View.GONE
        helper.getView<TextView>(R.id.tv_confirm_order).text= when(item.type){
            0->"立即付款"
            1->"确认收货"
            2->"去评价"
            3->"删除订单"
            4->"查看详情"
            else ->""}
    }

}