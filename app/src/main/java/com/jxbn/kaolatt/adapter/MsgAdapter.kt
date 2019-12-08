package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.MsgBean
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/8
 */
class MsgAdapter:BaseQuickAdapter<MsgBean,BaseViewHolder>(R.layout.item_msg_list) {
    override fun convert(helper: BaseViewHolder, item: MsgBean?) {
       item?:return
        helper.setText(R.id.tv_title,item.title)
                .setText(R.id.tv_content,item.content)
                .setText(R.id.tv_from,item.from)
                .setText(R.id.tv_date,item.data)
        val img = helper.getView<ImageView>(R.id.iv_img)
        GlideUtils.showRound(img,item.imgUrl,R.drawable.ic_launcher_background,6)
    }
}