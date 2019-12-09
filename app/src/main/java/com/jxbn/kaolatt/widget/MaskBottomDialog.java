package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jxbn.kaolatt.R;
import com.jxbn.kaolatt.bean.GoodsMaskBean;
import com.jxbn.kaolatt.flowtag.FlowTagLayout;
import com.jxbn.kaolatt.flowtag.OnTagSelectListener;
import com.jxbn.kaolatt.flowtag.TagAdapter;
import com.jxbn.kaolatt.glide.GlideUtils;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/9
 */
public class MaskBottomDialog extends BottomSheetDialog implements View.OnClickListener {

    private final ImageView mImageView;
    private final TextView mTvPrice;
    private final TextView mTvSaleNum;
    private final FlowTagLayout mFlowTab1;
    private final FlowTagLayout mFlowTab2;

    private GoodsMaskBean mBean;
    private final CounterView mCounterView;
    private final Context mContext;

    public MaskBottomDialog(Context context, GoodsMaskBean bean) {
        super(context);
        mContext = context;
        mBean=bean;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_mask_bottom, null);
        mImageView = view.findViewById(R.id.iv_img);
        mTvPrice = view.findViewById(R.id.tv_price);
        mTvSaleNum = view.findViewById(R.id.tv_sale_num);
        mFlowTab1 = view.findViewById(R.id.flow_tab_1);
        mFlowTab2 = view.findViewById(R.id.flow_tab_2);
        mCounterView = view.findViewById(R.id.counter_view);
        mFlowTab1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mFlowTab2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);

        initData();
        initListener();
        setContentView(view);
    }

    private void initData() {
        TagAdapter<String> adapter1 = new TagAdapter<String>(mContext);
        adapter1.onlyAddAll(mBean.getMask1());
        TagAdapter<String> adapter2 = new TagAdapter<String>(mContext);
        adapter2.onlyAddAll(mBean.getMask2());
        GlideUtils.showCircleWithBorder(mImageView,mBean.getImgUrl(),R.mipmap.ic_launcher, Color.parseColor("#FFFFFF"));
        mTvPrice.setText(mBean.getPrice());
        mTvSaleNum.setText(mBean.getSaleNum());
        mFlowTab1.setAdapter(adapter1);
        mFlowTab2.setAdapter(adapter2);
    }

    private void initListener() {
        mFlowTab1.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size()>0){
                    String tab1 =(String) parent.getAdapter().getItem(0);
                }

            }
        });
        mFlowTab2.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size()>0){
                    String tab2 =(String) parent.getAdapter().getItem(0);
                }
            }
        });

    }

    private OnChoseListener mOnChoseListener;

    @Override
    public void onClick(View view) {
        if (mOnChoseListener != null) {
            mOnChoseListener.select(view.getId());
        }

        dismiss();

    }

    public interface OnChoseListener {
        void select(int resID);
    }

    public void setOnChoseListener(OnChoseListener onChoseListener) {
        mOnChoseListener = onChoseListener;
    }
}
