package com.jxbn.kaolatt.ui.fragment

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jxbn.kaolatt.R
import com.jxbn.kaolatt.bean.ElemeGroupedItem
import com.jxbn.kaolatt.ext.showToast
import com.jxbn.kaolatt.ui.activity.GoodsDetailActivity
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

    private fun initLinkageDatas(rv: LinkageRecyclerView<ElemeGroupedItem.ItemInfo>) {
        val gson = Gson()
        val items = gson.fromJson<List<ElemeGroupedItem>>(getString(R.string.eleme_json),
                object : TypeToken<List<ElemeGroupedItem>>() {

                }.type)
        Logger.e("items==${items.size}")
        rv.init(items, ElemeLinkagePrimaryAdapterConfig(), ElemeLinkageSecondaryAdapterConfig())
        rv.isGridMode = true
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
                    if (selected) com.kunminx.linkage.R.color.colorPurple else com.kunminx.linkage.R.color.colorWhite))
            tvTitle.setTextColor(ContextCompat.getColor(mContext!!,
                    if (selected) com.kunminx.linkage.R.color.colorWhite else com.kunminx.linkage.R.color.colorGray))
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
            Glide.with(mContext!!).load(item.info.imgUrl).into(holder.getView(R.id.iv_goods_img) as ImageView)

            holder.itemView.setOnClickListener {
                //点击商品,详情页
                showToast(item.info.title)
                val intent = Intent(activity, GoodsDetailActivity::class.java)
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