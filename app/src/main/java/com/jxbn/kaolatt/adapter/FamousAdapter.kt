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
class FamousAdapter:BaseQuickAdapter<GoodsBean,BaseViewHolder>(R.layout.item_famous_list) {
    override fun convert(helper: BaseViewHolder, item: GoodsBean?) {
       item?:return
       val famousImg = helper.getView<ImageView>(R.id.iv_famous)
        GlideUtils.showRound(famousImg,"https://icweiliimg6.pstatp.com/weili/l/191170755344859143.webp",R.drawable.home_ban_member01,8)
    }
}