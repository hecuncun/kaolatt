package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.FamousListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/8
 * 首页的大牌
 */
class FamousListAdapter:BaseQuickAdapter<FamousListBean.DataBean,BaseViewHolder>(R.layout.item_famous_list) {
    override fun convert(helper: BaseViewHolder, item: FamousListBean.DataBean?) {
       item?:return
       val famousImg = helper.getView<ImageView>(R.id.iv_famous)
        GlideUtils.showRound(famousImg,Constant.BASE_URL+item.picture,R.mipmap.pic_dapaituijian,8)
    }
}