package com.jxbn.kaolatt.ui.activity

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.adapter.GoodsMoreAdapter
import com.jxbn.kaolatt.base.BaseActivity
import com.jxbn.kaolatt.bean.GoodsMoreListBean
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
import kotlin.properties.Delegates

/**
 * Created by hecuncun on 2019/12/8
 */
class SearchActivity : BaseActivity() {
    private val goodAdapter: GoodsMoreAdapter by lazy {
        GoodsMoreAdapter()
    }
    private val oldTagAdapter: TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }
    private val hotTagAdapter: TagAdapter<String> by lazy {
        TagAdapter<String>(this)
    }

    override fun attachLayoutRes(): Int = R.layout.activity_search
    private var cid:String?=null//从分类传递来的名字
    override fun initData() {
        type=1
        cid=intent?.extras?.getString("cid")
        if (cid!=null){
            showDataView()
            searchGoods()
        }
    }

    override fun initView() {
        showTagView()
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
    private val hotTagList = mutableListOf<String>()

    private val oldTagList = mutableListOf<String>()
    private fun initTagLayout() {
        //先获取热门标签
        val hotTagCall = SLMRetrofit.getInstance().api.hotTagCall()
        hotTagCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<HotTagListBean>() {
            override fun onSucceed(t: HotTagListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    t.data.forEach {
                        hotTagList.add(it.name)
                    }
                    hot_tag.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE)//单选
                    hot_tag.adapter = hotTagAdapter
                    hotTagAdapter.clearAndAddAll(hotTagList)
                    hot_tag.setOnTagClickListener { parent, view, position ->
                        showToast(hotTagList[position])
                        et_search.setText(hotTagList[position])
                        goodsName = hotTagList[position]
                        searchGoods()
                        showDataView()

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
            et_search.setText(oldTagList[position])
            goodsName = oldTagList[position]
            searchGoods()
            showDataView()
        }

        if (oldTagList.isEmpty()) {
            old_tag.visibility = View.GONE
            rl_old_container.visibility = View.GONE
        } else {
            old_tag.visibility = View.VISIBLE
            rl_old_container.visibility = View.VISIBLE
        }

    }

    /**
     * 初始化默认搜索条件
     */
    private fun initSearchLimit() {
        currentPage = 1
        type = 1
        max = null
        min = null
    }

    /**
     * 搜索
     */
    private var goodsName = ""
    private var currentPage = 1
    private var type by Delegates.observable(0

    ) { _, _, newValue ->
        tv_normal_type.setTextColor(resources.getColor(R.color.text_color_999999))
        tv_sale_num_type.setTextColor(resources.getColor(R.color.text_color_999999))
        tv_price.setTextColor(resources.getColor(R.color.text_color_999999))
        tv_sort_price.setTextColor(resources.getColor(R.color.text_color_999999))
        when(newValue){
            1->tv_normal_type.setTextColor(resources.getColor(R.color.colorPrimary))
            2->tv_sale_num_type.setTextColor(resources.getColor(R.color.colorPrimary))
            3,4->tv_price.setTextColor(resources.getColor(R.color.colorPrimary))
            5->tv_sort_price.setTextColor(resources.getColor(R.color.colorPrimary))
            else->{}
        }
    }
    private var max: Double? = null
    private var min: Double? = null
    private var total = 1
    private val list = mutableListOf<GoodsMoreListBean.DataBean.RowsBean>()
    private fun searchGoods() {
        val searchListCall = SLMRetrofit.getInstance().api.searchListCall(currentPage, goodsName,cid,type, max, min)
        searchListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsMoreListBean>() {
            override fun onSucceed(t: GoodsMoreListBean?) {
                if (t?.code == Constant.SUCCESSED_CODE) {
                    list.clear()
                    list.addAll(t.data.rows)
                    total=t.data.total
                    if (list.isEmpty()) {
                        showEmptyView()
                    } else {
                        showDataView()
                        goodAdapter.setNewData(list)
                    }

                }
            }

            override fun onFailed() {

            }
        })

    }

    /**
     * 显示空页面
     */
    private fun showEmptyView() {
        ll_goods_container.visibility = View.GONE
        ll_tag_container.visibility = View.GONE
        tv_no_data.visibility = View.VISIBLE
    }

    /**
     * 显示数据页
     */
    private fun showDataView() {
        ll_goods_container.visibility = View.VISIBLE
        ll_tag_container.visibility = View.GONE
        tv_no_data.visibility = View.GONE
    }

    /**
     * 显示标签页
     */
    private fun showTagView() {
        ll_goods_container.visibility = View.GONE
        ll_tag_container.visibility = View.VISIBLE
        tv_no_data.visibility = View.GONE
    }


    override fun initListener() {
        //退出
        iv_back.setOnClickListener { finish() }
        //删除搜索内容
        iv_del.setOnClickListener {
            et_search.setText("")
        }
        //点击搜索按钮
        iv_search.setOnClickListener {
            //搜索
            cid=null
            val etName = et_search.text.toString().trim()
            if (etName.isEmpty()) {
                showToast("不能为空")
                return@setOnClickListener
            } else {
                //入库
                CommOperation.insert(OldTagBean(etName))
                oldTagList.add(etName)
                oldTagAdapter.clearAndAddAll(oldTagList)
                if (oldTagList.isEmpty()) {
                    old_tag.visibility = View.GONE
                    rl_old_container.visibility = View.GONE
                } else {
                    old_tag.visibility = View.VISIBLE
                    rl_old_container.visibility = View.VISIBLE
                }
                initSearchLimit()
                goodsName = etName
                searchGoods()
            }

        }
        //删除历史
        iv_delete.setOnClickListener {
            LitePal.deleteAll(OldTagBean::class.java)
            oldTagList.clear()
            oldTagAdapter.clearAndAddAll(oldTagList)
            if (oldTagList.isEmpty()) {
                old_tag.visibility = View.GONE
                rl_old_container.visibility = View.GONE
            } else {
                old_tag.visibility = View.VISIBLE
                rl_old_container.visibility = View.VISIBLE
            }
        }
       //按价格筛选
        tv_sort_price.setOnClickListener {
            SortEditDialog.newInstance().setOnEnsureClickedListener { low, high ->
                showToast("low=$low,high=$high")
                //价格搜索
                max=high.toDouble()
                min=low.toDouble()
                type=5
                currentPage=1
                searchGoods()
            }.show(supportFragmentManager, "v")
        }
        tv_normal_type.setOnClickListener {
            type =1
            currentPage=1
            searchGoods()
        }
        //销量
        tv_sale_num_type.setOnClickListener {
            type=2
            currentPage=1
            searchGoods()
        }
       //价格高低 3降 4升

        rl_price.setOnClickListener {
            currentPage=1
           if (type!=3 && type!=4){
               type=3
               iv_arrow.setImageResource(R.mipmap.icon_down)
           } else if (type==3){
               type=4
               iv_arrow.setImageResource(R.mipmap.icon_up_pre)
           }else{
               type=3
               iv_arrow.setImageResource(R.mipmap.icon_down)
           }
            searchGoods()
        }


        /**
         * 加载更多
         */

      //  goodAdapter.disableLoadMoreIfNotFullPage(recyclerView)
        goodAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            if(total==1){
            goodAdapter.setEnableLoadMore(false)
                goodAdapter.loadMoreEnd()
            }
            currentPage++
            if (currentPage > total) {
               return@RequestLoadMoreListener
            }

            val searchListCall = SLMRetrofit.getInstance().api.searchListCall(currentPage, goodsName,cid,type, max, min)
            searchListCall.compose(ThreadSwitchTransformer()).subscribe(object : CallbackListObserver<GoodsMoreListBean>() {
                override fun onSucceed(t: GoodsMoreListBean?) {
                    if (t?.code==Constant.SUCCESSED_CODE){
                        goodAdapter.addData(t.data.rows)
                        if (currentPage==total){
                            goodAdapter.loadMoreEnd()
                            goodAdapter.setEnableLoadMore(false)
                        }else{
                            goodAdapter.loadMoreComplete()
                            goodAdapter.setEnableLoadMore(true)
                        }

                    }
                }

                override fun onFailed() {

                }
            })


        },recyclerView)


        goodAdapter.setOnItemClickListener { adapter, view, position ->
            list[position].gid
            Intent(this@SearchActivity,GoodsDetailActivity::class.java).run {
                putExtra("gid",list[position].gid)
                startActivity(this)
            }
        }
    }



}