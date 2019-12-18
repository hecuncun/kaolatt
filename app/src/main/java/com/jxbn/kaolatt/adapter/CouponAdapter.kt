package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CouponListBean
import com.jxbn.kaolatt.utils.DateUtils

/**
 * Created by hecuncun on 2019/12/4
 */
class CouponAdapter:BaseQuickAdapter<CouponListBean.DataBean,BaseViewHolder>(R.layout.item_coupon_list) {
    override fun convert(helper: BaseViewHolder, item: CouponListBean.DataBean?) {
        item?:return
        helper.setText(R.id.tv_money,"￥${item.valueSubtraction}")
                .setText(R.id.tv_limit,"满${item.valueMax}可用")
                .setText(R.id.tv_start_time,item.startTime)
                .setText(R.id.tv_end_time,item.endTime)
        val ivState =  helper.getView<ImageView>(R.id.iv_state)
        helper.getView<ImageView>(R.id.iv_type).setImageResource(
                when(item.cuid){
                   "",null->if(System.currentTimeMillis()-DateUtils.stringToLong(item.endTime,"yyyy-MM-dd HH:mm:ss")<0){
                       R.mipmap.icon_youhui//优惠
                       }else{R.mipmap.icon_huise}

                    else->R.mipmap.icon_huise//已使用
                }
        )

        when(item.cuid){
            "",null->if(System.currentTimeMillis()-DateUtils.stringToLong(item.endTime,"yyyy-MM-dd HH:mm:ss")<0){
                ivState.visibility=View.GONE//优惠
            }else{ivState.visibility=View.VISIBLE
                ivState.setImageResource(R.mipmap.icon_guoqi)}

            else->{ivState.visibility=View.VISIBLE
                ivState.setImageResource(R.mipmap.icon_shiyong)}
//            0->{ivState.visibility=View.GONE}
//            1->{ivState.visibility=View.VISIBLE
//                ivState.setImageResource(R.mipmap.icon_guoqi)
//            }
//            2->{
//                ivState.visibility=View.VISIBLE
//                ivState.setImageResource(R.mipmap.icon_shiyong)
//            }
        }
    }
}