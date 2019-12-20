package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.CollectionAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.base.BaseNoDataBean
import com.jxbn.kaolatt.bean.MyCollectionListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.LogoutDialog
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by heCunCun on 2019/12/6
 */
class CollectionActivity : BaseActivity() {
    private val collectionAdapter: CollectionAdapter by lazy {
        CollectionAdapter()
    }

    override fun attachLayoutRes(): Int = R.layout.activity_collection

  private  val list = mutableListOf<MyCollectionListBean.DataBean>()
    override fun initData() {
        val myCollectionListCall = SLMRetrofit.getInstance().api.myCollectionListCall(uid)
        myCollectionListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<MyCollectionListBean>() {
            override fun onSucceed(t: MyCollectionListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    list.addAll(t.data)

                    if (list.isEmpty()) {
                        tv_no_data.visibility = View.VISIBLE
                    } else {
                        tv_no_data.visibility = View.GONE
                        list.forEach {
                            it.isChecked = false
                        }
                        collectionAdapter.setNewData(list)
                    }


                }
            }

            override fun onFailed() {
            }
        })


//        for (i in 1..20){
//            list.add(CollectionBean("","卡姿兰带大眼睛","¥500",i.times(20),false))
//        }
//        collectionAdapter.addData(list)
    }

    override fun initView() {
        toolbar_title.text = "我的收藏"
        toolbar_right_tv.text = "编辑"
        toolbar_right_tv.visibility = View.VISIBLE
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.run {
            adapter = collectionAdapter
            layoutManager = LinearLayoutManager(this@CollectionActivity)
        }
    }
   private val sb:StringBuilder= StringBuilder()
    override fun initListener() {
        toolbar_right_tv.setOnClickListener {
            if (list.isEmpty()){
                return@setOnClickListener
            }
            if (toolbar_right_tv.text == "编辑") {
                collectionAdapter.setShowCheckIcon(true)
                toolbar_right_tv.text = "完成"
                tv_delete.visibility = View.VISIBLE
            } else if (toolbar_right_tv.text == "完成") {
                collectionAdapter.setShowCheckIcon(false)
                toolbar_right_tv.text = "编辑"
                tv_delete.visibility = View.GONE
            }
        }

        collectionAdapter.setOnItemChildClickListener { adapter, view, position ->
            val bean = (adapter.getItem(position) as MyCollectionListBean.DataBean)
            bean.isChecked = !bean.isChecked
            collectionAdapter.notifyItemChanged(position)
        }

        tv_delete.setOnClickListener {
            //删除所选商品
            val dialog = LogoutDialog(this@CollectionActivity)
            dialog.show()
            dialog.setTitle("确定要移除所选商品？")
            dialog.setConfirmListener(View.OnClickListener {
                showToast("删除")
                list.filter {
                    it.isChecked
                }.forEach {
                    sb.append(it.gid)
                    sb.append(",")
                }
                val temp    =   sb.toString().trim()
                val gids =  temp.substring(0,temp.length-1)
                Logger.e("gids==$gids")
                dialog.dismiss()
                //批量删除
                val deleteCollection = SLMRetrofit.getInstance().api.deleteCollection(gids, uid)
                deleteCollection.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<BaseNoDataBean>(){
                    override fun onSucceed(t: BaseNoDataBean?) {
                       if (t?.code==Constant.SUCCESSED_CODE){
                           showToast("移除成功")
                           collectionAdapter.setNewData(list.filter { !it.isChecked })
                           if (list.filter { !it.isChecked }.isEmpty()) {
                             tv_no_data.visibility = View.VISIBLE
                               tv_delete.visibility=View.GONE
                               toolbar_right_tv.isEnabled=false
                           } else {
                               tv_no_data.visibility = View.GONE
                               tv_delete.visibility=View.VISIBLE
                               toolbar_right_tv.isEnabled=true
                           }
                       }else{
                           showToast("移除失败:"+t?.message)
                       }
                    }

                    override fun onFailed() {

                    }
                })

            })

        }
    }
}