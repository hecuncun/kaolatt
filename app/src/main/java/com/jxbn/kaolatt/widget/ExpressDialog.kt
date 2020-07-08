package com.jxbn.kaolatt.widget

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.ExpressAdapter
import com.jxbn.kaolatt.base.BaseDialog
import com.jxbn.kaolatt.bean.ExpressBean
import kotlinx.android.synthetic.main.dialig_express.*

/**
 * Created by heCunCun on 2020/7/8
 */
class ExpressDialog:BaseDialog() {

       private val expressAdapter :ExpressAdapter by lazy {
           ExpressAdapter()
       }
    override fun getLayoutId(): Int= R.layout.dialig_express

    override fun initView() {
        if (arguments!=null){
            val bean = arguments!!.getParcelable<ExpressBean>("bean")
            tv_company.text=bean?.type
            tv_order_num.text=bean?.no
            recyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = expressAdapter
                expressAdapter.setNewData(bean?.list)
            }
        }
        iv_close.setOnClickListener {
            dismiss()
        }

    }

    override fun getGravity(): Int?=Gravity.CENTER

    companion object {
        fun newInstance(bean:ExpressBean?): ExpressDialog {
            val dialog = ExpressDialog()
            val bundle = Bundle()
            bundle.putParcelable("bean",bean)
            dialog.arguments = bundle
            return dialog
        }
    }
}