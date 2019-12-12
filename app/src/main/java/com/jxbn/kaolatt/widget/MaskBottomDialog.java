package com.jxbn.kaolatt.widget;

import android.content.Context;
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
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by heCunCun on 2019/12/9
 */
public class MaskBottomDialog extends BottomSheetDialog implements View.OnClickListener {

    private final ImageView mImageView;
    private final ImageView mIvClose;
    private final TextView mTvPrice;
    private final TextView mTvSaleNum;
    private final TextView mTvConfirm;
    private final FlowTagLayout mFlowTab1;
    private final FlowTagLayout mFlowTab2;

    private GoodsMaskBean mBean;
    private final CounterView mCounterView;
    private final Context mContext;
    private String mTab1;
    private String mTab2;

    public MaskBottomDialog(Context context, GoodsMaskBean bean) {
        super(context);
        mContext = context;
        mBean=bean;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_mask_bottom, null);
        mImageView = view.findViewById(R.id.iv_img);
        mIvClose=view.findViewById(R.id.iv_close);
        mTvPrice = view.findViewById(R.id.tv_price);
        mTvSaleNum = view.findViewById(R.id.tv_sale_num);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
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
        mFlowTab1.setAdapter(adapter1);
        adapter1.clearAndAddAll(mBean.getMask1());
        Logger.e("mBean.getMask1()=="+mBean.getMask1().size());

        TagAdapter<String> adapter2 = new TagAdapter<String>(mContext);
        mFlowTab2.setAdapter(adapter2);
        adapter2.clearAndAddAll(mBean.getMask2());


        GlideUtils.showRound(mImageView,mBean.getImgUrl(),R.mipmap.ic_launcher,8);
        mTvPrice.setText(mBean.getPrice());
        mTvSaleNum.setText(mBean.getSaleNum());


    }

    private void initListener() {
        mFlowTab1.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {

                mTab1 = (String) parent.getAdapter().getItem(selectedList.get(0));
            }
        });
        mFlowTab2.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size()>0){
                    mTab2 = (String) parent.getAdapter().getItem(selectedList.get(0));
                }
            }
        });

        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvConfirm.setOnClickListener(this);

    }

    private OnChoseListener mOnChoseListener;

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.tv_confirm)
        if (mOnChoseListener != null) {
            mOnChoseListener.select(mTab1,mTab2,mCounterView.getInitNum()+"");
        }

        dismiss();

    }

    public interface OnChoseListener {
        void select(String tab1,String tab2,String num);
    }

    public void setOnChoseListener(OnChoseListener onChoseListener) {
        mOnChoseListener = onChoseListener;
    }
}
