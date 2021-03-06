package com.jxbn.kaolatt.ui.fragment

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.ElemeGroupedItem
import com.jxbn.kaolatt.bean.SortListBean
import com.jxbn.kaolatt.constants.Constant
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.glide.GlideUtils
import com.jxbn.kaolatt.net.CallbackListObserver
import com.jxbn.kaolatt.net.SLMRetrofit
import com.jxbn.kaolatt.net.ThreadSwitchTransformer
import com.jxbn.kaolatt.ui.activity.SearchActivity
import com.kunminx.linkage.LinkageRecyclerView
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder
import com.kunminx.linkage.bean.BaseGroupedItem
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig
import com.lhzw.bluetooth.base.BaseFragment
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_sort.*


/**
 * Created by hecuncun on 2019/11/13
 */
class SortFragment : BaseFragment() {

    private val SPAN_COUNT_FOR_GRID_MODE = 3
    private val MARQUEE_REPEAT_LOOP_MODE = -1
    private val MARQUEE_REPEAT_NONE_MODE = 0

    override fun initListener() {
        ll_search.setOnClickListener { jumpToSearchActivity()  }
    }
    private fun jumpToSearchActivity() {
        val intent = Intent(activity, SearchActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun getInstance(): SortFragment = SortFragment()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_sort

    override fun initView(view: View) {
        initLinkageDatas(linkage as LinkageRecyclerView<ElemeGroupedItem.ItemInfo>)

    }


    override fun lazyLoad() {

    }
   private var list = mutableListOf<ElemeGroupedItem>()
   private val isLoaded =false
    private var total = 0
    private var current = 0
    private var isLoading =false

    private var idSort=0
    private fun initLinkageDatas(rv: LinkageRecyclerView<ElemeGroupedItem.ItemInfo>) {
        val sortListFirstCall = SLMRetrofit.getInstance().api.sortListCall("0")
        sortListFirstCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<SortListBean>(){
            override fun onSucceed(t: SortListBean?) {
                if (t?.code==Constant.SUCCESSED_CODE){
                    val listFirst = t.data//一级分类集合
                    total=listFirst.size
                    for (i in listFirst.indices){
                        val  it = listFirst[i]
                        val group = it.name
                        //进行循环请求二级列表
                        //isLoading =true
                        val sortListSecondCall = SLMRetrofit.getInstance().api.sortListCall(it.cid)
                        sortListSecondCall.compose(ThreadSwitchTransformer()).subscribe(object :CallbackListObserver<SortListBean>(){
                            override fun onSucceed(t: SortListBean?) {
                               if (t?.code==Constant.SUCCESSED_CODE){
                                   val headBean =  ElemeGroupedItem(true, group)
                                   headBean.id=i
                                   list.add(headBean)
                                   //***大坑！！！ 添加一级分类，必须接着马上添加二级分类，如果顺序错乱就会出现头连着头，数据错乱问题，
                                   val listSecond = t.data
                                   listSecond.forEach {
                                       val itemInfo= ElemeGroupedItem.ItemInfo(it.name?:"",group,it.cid,it.remark1?:"")
                                     val childBean=  ElemeGroupedItem(itemInfo)
                                       childBean.id=i
                                       list.add( childBean)
                                   }
                                   current++
                                   LogUtils.e("current==$current,total==$total")
                                   if (current==total){//所有接口请求完毕就显示
                                       list.sort()
                                       rv.init(list as MutableList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>, ElemeLinkagePrimaryAdapterConfig(), ElemeLinkageSecondaryAdapterConfig())
                                       rv.isGridMode = true
                                       Logger.e("json==>"+Gson().toJson(list))
                                   }
                               }
                            }

                            override fun onFailed() {

                            }
                        })
                    }
                }
            }

            override fun onFailed() {

            }
        })

//        val gson = Gson()
//        val items = gson.fromJson<List<ElemeGroupedItem>>(getString(R.string.eleme_json1),
//                object : TypeToken<List<ElemeGroupedItem>>() {
//
//                }.type)
//        Logger.e("items==${items.size}")
//        //rv.init(items as MutableList<BaseGroupedItem<ElemeGroupedItem.ItemInfo>>, ElemeLinkagePrimaryAdapterConfig(), ElemeLinkageSecondaryAdapterConfig())
//        rv.init(items, ElemeLinkagePrimaryAdapterConfig(), ElemeLinkageSecondaryAdapterConfig())
//        rv.isGridMode = true
        //Logger.e("json==>"+Gson().toJson(list))
    }

//左边标签的adapter
    private inner class ElemeLinkagePrimaryAdapterConfig : ILinkagePrimaryAdapterConfig {

        private var mContext: Context? = null

        override fun setContext(context: Context) {
            mContext = context
        }

        override fun getLayoutId(): Int {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_primary
        }

        override fun getGroupTitleViewId(): Int {
            return com.kunminx.linkage.R.id.tv_group
        }

        override fun getRootViewId(): Int {
            return com.kunminx.linkage.R.id.layout_group
        }

        override fun onBindViewHolder(holder: LinkagePrimaryViewHolder, selected: Boolean, title: String) {
            val tvTitle = holder.mGroupTitle as TextView
            tvTitle.text = title
            //设置选中的标签颜色
            tvTitle.setBackgroundColor(mContext!!.resources.getColor(
                    if (selected) R.color.color_F5F5F6 else R.color.colorWhite))
            tvTitle.setTextColor(ContextCompat.getColor(mContext!!,
                    if (selected) R.color.colorPrimary else R.color.text_color_gray))
            tvTitle.ellipsize = if (selected) TextUtils.TruncateAt.MARQUEE else TextUtils.TruncateAt.END
            tvTitle.isFocusable = selected
            tvTitle.isFocusableInTouchMode = selected
            tvTitle.marqueeRepeatLimit = if (selected) MARQUEE_REPEAT_LOOP_MODE else MARQUEE_REPEAT_NONE_MODE
        }

        override fun onItemClick(holder: LinkagePrimaryViewHolder, view: View, title: String) {
            //TODO
        }

    }
//右边内容的adapter
    private inner class ElemeLinkageSecondaryAdapterConfig : ILinkageSecondaryAdapterConfig<ElemeGroupedItem.ItemInfo> {

        private var mContext: Context? = null

        override fun setContext(context: Context) {
            mContext = context
        }

        override fun getGridLayoutId(): Int {
            return R.layout.adapter_eleme_secondary_grid
        }

        override fun getLinearLayoutId(): Int {
            return R.layout.adapter_eleme_secondary_linear
        }

        override fun getHeaderLayoutId(): Int {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_secondary_header
        }

        override fun getFooterLayoutId(): Int {
            return 0
        }

        override fun getHeaderTextViewId(): Int {
            return R.id.secondary_header
        }

        override fun getSpanCountOfGridMode(): Int {
            return SPAN_COUNT_FOR_GRID_MODE
        }

        override fun onBindViewHolder(holder: LinkageSecondaryViewHolder,
                                      item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>) {

            (holder.getView(R.id.iv_goods_name) as TextView).text=item.info.title
            //Glide.with(mContext!!).load(item.info.imgUrl).into(holder.getView(R.id.iv_goods_img) as ImageView)
            GlideUtils.showRound(holder.getView(R.id.iv_goods_img) as ImageView,Constant.BASE_URL+item.info.imgUrl,R.mipmap.pic_good,6)
            holder.itemView.setOnClickListener {
                //点击商品,详情页
                showToast(item.info.title)
                val intent = Intent(activity, SearchActivity::class.java)
                intent.putExtra("cid",item.info.content)//cid  商品二级分类id传过去
                startActivity(intent)
            }

        }

        override fun onBindHeaderViewHolder(holder: LinkageSecondaryHeaderViewHolder,
                                            item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>) {

            (holder.getView(R.id.secondary_header) as TextView).text = item.header
        }

        override fun onBindFooterViewHolder(holder: LinkageSecondaryFooterViewHolder,
                                            item: BaseGroupedItem<ElemeGroupedItem.ItemInfo>) {

        }
    }
}