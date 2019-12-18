package com.jxbn.kaolatt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.ScoreListBean

/**
 * Created by heCunCun on 2019/12/4
 */
class ScoreAdapter:BaseQuickAdapter<ScoreListBean.DataBean.RowsBean,BaseViewHolder>(R.layout.item_score_list) {
    override fun convert(helper: BaseViewHolder, item: ScoreListBean.DataBean.RowsBean?) {
       item?:return
        helper.setText(R.id.tv_desc,if(item.type=="1") "购买商品" else "积分抵扣")
                .setText(R.id.tv_date,item.createtime)
                .setText(R.id.tv_score,"积分${item.integral }")
    }
}