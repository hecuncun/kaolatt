package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.EvaluateBean
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by heCunCun on 2019/12/9
 */
class EvaluateAdapter:BaseQuickAdapter<EvaluateBean,BaseViewHolder>(R.layout.item_evaluate_list) {
    override fun convert(helper: BaseViewHolder, item: EvaluateBean?) {
        item?:return
        helper.setText(R.id.tv_name,item.name)
                .setText(R.id.tv_content,item.content)
        val img = helper.getView<ImageView>(R.id.iv_head_photo)
        GlideUtils.showCircle(img,item.imgUrl,R.mipmap.ic_launcher)
    }
}