package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
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
        helper.getView<ImageView>(R.id.iv_type).setImageResource(
                when(item.type){
                    0->R.mipmap.icon_youhui//优惠
                    1->R.mipmap.icon_huise//过期
                    2->R.mipmap.icon_huise//已使用
                    else->R.mipmap.icon_youhui
                }
        )
      val ivState =  helper.getView<ImageView>(R.id.iv_state)
        when(item.type){
            0->{ivState.visibility=View.GONE}
            1->{ivState.visibility=View.VISIBLE
                ivState.setImageResource(R.mipmap.icon_guoqi)
            }
            2->{
                ivState.visibility=View.VISIBLE
                ivState.setImageResource(R.mipmap.icon_shiyong)
            }
        }
    }
}