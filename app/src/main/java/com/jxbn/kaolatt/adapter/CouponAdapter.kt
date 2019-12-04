package com.jxbn.kaolatt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CouponBean

/**
 * Created by hecuncun on 2019/12/4
 */
class CouponAdapter:BaseQuickAdapter<CouponBean,BaseViewHolder>(R.layout.item_coupon_list) {
    override fun convert(helper: BaseViewHolder, item: CouponBean?) {
        item?:return
        helper.setText(R.id.tv_money,item.money.toString())
                .setText(R.id.tv_limit,item.limit)
                .setText(R.id.tv_start_time,item.startTime)
                .setText(R.id.tv_end_time,item.endTime)
    }
}