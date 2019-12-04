package com.jxbn.kaolatt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.UserScoreBean

/**
 * Created by heCunCun on 2019/12/4
 */
class ScoreAdapter:BaseQuickAdapter<UserScoreBean,BaseViewHolder>(R.layout.item_score_list) {
    override fun convert(helper: BaseViewHolder, item: UserScoreBean?) {
       item?:return
        helper.setText(R.id.tv_desc,item.desc)
                .setText(R.id.tv_date,item.date)
                .setText(R.id.tv_score,"积分+${item.score}")
    }
}