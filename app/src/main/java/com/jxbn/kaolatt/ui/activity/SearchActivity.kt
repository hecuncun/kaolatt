package com.jxbn.kaolatt.ui.activity

import android.support.v7.widget.GridLayoutManager
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsBean
import com.jxbn.kaolatt.bean.HotTagListBean
import com.jxbn.kaolatt.bean.OldTagBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.db.CommOperation
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.flowtag.FlowTagLayout
import com.jxbn.kaolatt.flowtag.TagAdapter
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.widget.SortEditDialog
import kotlinx.android.synthetic.main.activity_search.*
import org.litepal.LitePal

/**
 * Created by hecuncun on 2019/12/8
 */
class SearchActivity:BaseActivity() {
    private val goodAdapter: GoodsAdapter by lazy {
        GoodsAdapter()
    }
    private val oldTagAdapter:TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }
    private val hotTagAdapter:TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }

    override fun attachLayoutRes(): Int = R.layout.activity_search

    private val list = mutableListOf<GoodsBean>()
    override fun initData() {
        for (i in 0..3) {
            list.add(GoodsBean("SK2小黑瓶安瓶精华", 500f, "￥800", "200", "https://www.dior.cn/beauty/version-5.1563986503609/resize-image/ep/3000/2000/90/0/%252FY0112000%252FY0112000_C011200066_E01_ZHC.jpg"))
        }
        goodAdapter.addData(list)
    }

    override fun initView() {
        initTagLayout()
        initTagLayout()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        recyclerView.run {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = goodAdapter
        }
    }

    /**
     * 标签初始化
     */
    private val  hotTagList= mutableListOf<String>()
    private val  oldTagList= mutableListOf<String>()
    private fun initTagLayout() {
        //先获取热门标签
        val hotTagCall = SLMRetrofit.getInstance().api.hotTagCall()
        hotTagCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<HotTagListBean>(){
            override fun onSucceed(t: HotTagListBean?) {
              if (t?.code==Constant.SUCCESSED_CODE){
                 t.data.forEach {
                     hotTagList.add(it.name)
                     }
                  hot_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE)//单选
                  hot_tag.adapter = hotTagAdapter
                  hotTagAdapter.clearAndAddAll(hotTagList)
                  hot_tag.setOnTagClickListener { parent, view, position ->
                      showToast(hotTagList[position])
                 }
              }
            }

            override fun onFailed() {

            }
        })
       //数据库获取历史标签
        val oldTagListBean = CommOperation.query<OldTagBean>()
        oldTagListBean.forEach {
            oldTagList.add(it.tag)
        }

        old_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE)//不选
        old_tag.adapter = oldTagAdapter
        oldTagAdapter.clearAndAddAll(oldTagList)
        old_tag.setOnTagClickListener { parent, view, position ->
            showToast(oldTagList[position])
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener { finish() }
        iv_del.setOnClickListener {
            et_search.setText("")
        }
        iv_search.setOnClickListener { //搜索
         val goodsName = et_search.text.toString().trim()
            if(goodsName.isEmpty()){
                showToast( "不能为空" )
                return@setOnClickListener
            }else{
                //入库
                CommOperation.insert(OldTagBean(goodsName))
                oldTagList.add(goodsName)
                oldTagAdapter.notifyDataSetChanged()
            }

            // }
    }
        //删除历史
        iv_delete.setOnClickListener {
            LitePal.deleteAll(OldTagBean::class.java)
            oldTagList.clear()
            oldTagAdapter.notifyDataSetChanged()
        }

        tv_sort_price.setOnClickListener {
            SortEditDialog.newInstance().setOnEnsureClickedListener { low, high ->
               showToast("low=$low,high=$high")

            }.show(supportFragmentManager,"v")

        }
    }
}