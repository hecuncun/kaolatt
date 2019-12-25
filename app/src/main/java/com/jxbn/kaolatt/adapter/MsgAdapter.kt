package com.jxbn.kaolatt.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.MsgListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by hecuncun on 2019/12/8
 */
class   MsgAdapter:BaseQuickAdapter<MsgListBean.DataBean.RowsBean,BaseViewHolder>(R.layout.item_msg_list) {
    override fun convert(helper: BaseViewHolder, item: MsgListBean.DataBean.RowsBean?) {
       item?:return
        helper.setText(R.id.tv_title,item.title)
                .setText(R.id.tv_content,item.title)
                //.setText(R.id.tv_from,item.from)
                .setText(R.id.tv_date,item.createtime)
        val img = helper.getView<ImageView>(R.id.iv_img)
        val ivState = helper.getView<ImageView>(R.id.iv_state)
        if (item.status==null || item.status.isEmpty()){
            ivState.visibility = View.VISIBLE
        }else{
            ivState.visibility = View.GONE
        }
        GlideUtils.showRound(img,Constant.BASE_URL+item.photo,R.mipmap.pic_good,6)
    }
}