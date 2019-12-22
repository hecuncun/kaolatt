package com.jxbn.kaolatt.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.AddressListBean

/**
 * Created by heCunCun on 2019/12/10
 */
class AddressAdapter : BaseQuickAdapter<AddressListBean.DataBean, BaseViewHolder>(R.layout.item_address_list) {
    override fun convert(helper: BaseViewHolder, item: AddressListBean.DataBean?) {
        item ?: return
        helper.setText(R.id.tv_user_name, item.name)
                .setText(R.id.tv_phone, item.phone)
                .setText(R.id.tv_address, item.area+item.areaDetail)
        val ivCheck = helper.getView<ImageView>(R.id.iv_check)
        ivCheck.setImageResource(if (item.remark4=="1") R.mipmap.success_fill else R.mipmap.success_fill02)
        helper.addOnClickListener(R.id.iv_check)
        helper.addOnClickListener(R.id.tv_edit)
        helper.addOnClickListener(R.id.tv_delete)
    }
}