package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.EvaluateListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.glide.GlideUtils

/**
 * Created by heCunCun on 2019/12/9
 */
class EvaluateAdapter:BaseQuickAdapter<EvaluateListBean.DataBean.RowsBean,BaseViewHolder>(R.layout.item_evaluate_list) {
    override fun convert(helper: BaseViewHolder, item: EvaluateListBean.DataBean.RowsBean?) {
        item?:return
        helper.setText(R.id.tv_name,item.nickname)
                .setText(R.id.tv_content,item.content)
        val img = helper.getView<ImageView>(R.id.iv_head_photo)
        GlideUtils.showCircle(img,Constant.BASE_URL+item.path,R.mipmap.ic_launcher)
    }
}