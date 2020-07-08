package com.jxbn.kaolatt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.ExpressBean

/**
 * Created by heCunCun on 2020/7/8
 */
class ExpressAdapter:BaseQuickAdapter<ExpressBean.ListBean,BaseViewHolder>(R.layout.item_express) {
    override fun convert(helper: BaseViewHolder, item: ExpressBean.ListBean?) {
        item?:return
        helper.setText(R.id.tv_time,item.time)
        helper.setText(R.id.tv_content,item.content)
    }
}