package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.CartBean
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.widget.CounterView

/**
 * Created by hecuncun on 2019/12/10
 */
class CartListAdapter:BaseQuickAdapter<CartBean,BaseViewHolder>(R.layout.item_cart_list) {
    override fun convert(helper: BaseViewHolder, item: CartBean?) {
       item?:return
        helper.setText(R.id.tv_goods_name,item.goodsName)
                .setText(R.id.tv_mask,item.mask)
                .setText(R.id.tv_goods_price,item.price)

        val ivCheck = helper.getView<ImageView>(R.id.iv_check)
        ivCheck.setImageResource(if(item.isCheck) R.mipmap.icon_check_pre else  R.mipmap.icon_check)
        helper.addOnClickListener(R.id.iv_check)

        val counterView = helper.getView<CounterView>(R.id.counter_view)
        counterView.initNum =item.num
        counterView.setOnNumberChangedListener{number->
            item.num=number
        }
        val ivGoods = helper.getView<ImageView>(R.id.iv_goods)

      GlideUtils.showRound(ivGoods,item.imgUrl,R.mipmap.ic_launcher,5)


    }
}