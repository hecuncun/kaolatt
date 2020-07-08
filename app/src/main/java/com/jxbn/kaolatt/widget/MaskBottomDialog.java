package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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
    private final TextView mTvAddCart;
    private final TextView mTvConfirm;
    private final TextView mTvPay;
    private final TextView mTvDesc;
    private final TextView mTvMask1;
    private final TextView mTvMask2;
    private final TextView mTvMask3;
    private final FlowTagLayout mFlowTab1;
    private final FlowTagLayout mFlowTab2;
    private final FlowTagLayout mFlowTab3;

    private GoodsMaskBean mBean;
    private final CounterView mCounterView;
    private final Context mContext;
    private String mTab1;
    private String mTab2;
    private String mTab3;
    private final LinearLayout mTwoBtnContainer;

    private boolean mIsAddCar;//是否加入购物车

    public void setShowOneBtn(boolean oneBtn, boolean isAddCar) {
        mIsAddCar = isAddCar;
        if (oneBtn) {
            mTwoBtnContainer.setVisibility(View.GONE);
            mTvPay.setVisibility(View.VISIBLE);
        } else {
            mTwoBtnContainer.setVisibility(View.VISIBLE);
            mTvPay.setVisibility(View.GONE);
        }
    }

    public MaskBottomDialog(Context context, GoodsMaskBean bean) {
        super(context);
        mContext = context;
        mBean = bean;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_mask_bottom, null);
        mImageView = view.findViewById(R.id.iv_img);
        mIvClose = view.findViewById(R.id.iv_close);
        mTwoBtnContainer = (LinearLayout) view.findViewById(R.id.ll_two_btn_container);
        mTvPrice = view.findViewById(R.id.tv_price);
        mTvDesc = view.findViewById(R.id.tv_desc);
        mTvSaleNum = view.findViewById(R.id.tv_sale_num);
        mTvAddCart = view.findViewById(R.id.tv_add_car);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvPay = view.findViewById(R.id.tv_pay);

//        if (oneButton){
//            mTwoBtnContainer.setVisibility(View.GONE);
//            mTvPay.setVisibility(View.VISIBLE);
//        }else {
//            mTwoBtnContainer.setVisibility(View.VISIBLE);
//            mTvPay.setVisibility(View.GONE);
//        }


        mTvMask1 = view.findViewById(R.id.tv_mask1);
        mTvMask2 = view.findViewById(R.id.tv_mask2);
        mTvMask3 = view.findViewById(R.id.tv_mask3);
        mFlowTab1 = view.findViewById(R.id.flow_tab_1);
        mFlowTab2 = view.findViewById(R.id.flow_tab_2);
        mFlowTab3 = view.findViewById(R.id.flow_tab_3);
        mCounterView = view.findViewById(R.id.counter_view);
        mFlowTab1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mFlowTab2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mFlowTab3.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        initData();
        initListener();
        setContentView(view);
    }

    private void initData() {
        mTvDesc.setText(mBean.getDesc());
        mTvMask1.setText(mBean.getMaskName1());
        TagAdapter<String> adapter1 = new TagAdapter<String>(mContext);
        mFlowTab1.setAdapter(adapter1);
        if (mBean.getMask1().size() == 0) {
            mFlowTab1.setVisibility(View.GONE);
        } else {
            mFlowTab1.setVisibility(View.VISIBLE);
        }
        adapter1.clearAndAddAll(mBean.getMask1());
        Logger.e("mBean.getMask1()==" + mBean.getMask1().size());

        mTvMask2.setText(mBean.getMaskName2());
        TagAdapter<String> adapter2 = new TagAdapter<String>(mContext);
        mFlowTab2.setAdapter(adapter2);
        if (mBean.getMask2().size() == 0) {
            mFlowTab2.setVisibility(View.GONE);
        } else {
            mFlowTab2.setVisibility(View.VISIBLE);
        }
        adapter2.clearAndAddAll(mBean.getMask2());

        mTvMask3.setText(mBean.getMaskName3());
        TagAdapter<String> adapter3 = new TagAdapter<String>(mContext);
        mFlowTab3.setAdapter(adapter3);
        if (mBean.getMask3().size() == 0) {
            mFlowTab3.setVisibility(View.GONE);
        } else {
            mFlowTab3.setVisibility(View.VISIBLE);
        }
        adapter3.clearAndAddAll(mBean.getMask3());


        GlideUtils.showRound(mImageView, mBean.getImgUrl(), R.mipmap.pic_good, 8);
        mTvPrice.setText("￥" + mBean.getPrice());
        mRealPrice=mBean.getPrice();
        mTvSaleNum.setText("累计销售" + mBean.getSaleNum() + "件");


    }
   private String mRealPrice ="";
    private void initListener() {
        mFlowTab1.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size() > 0) {
                    mTab1 = (String) parent.getAdapter().getItem(selectedList.get(0));
                    if (mBean.getMaskNum()==1){
                        for (int i=0;i<mBean.getList().size();i++){
                            if (mTab1.equals(mBean.getList().get(i).getName())){
                                mTvPrice.setText("￥" + mBean.getList().get(i).getPrice());
                                mRealPrice=mBean.getList().get(i).getPrice();
                                String desc="";
                                if (TextUtils.isEmpty(mBean.getList().get(i).getStock())){
                                    desc="无货";
                                }
//                                else if(Integer.parseInt(mBean.getList().get(i).getStock())==0){
//                                    desc="无货";
//                                }
                                else{
                                    desc="有货";
                                }
                                mTvDesc.setText(desc);
                            }
                        }
                    }
                }

            }
        });
        mFlowTab2.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size() > 0) {
                    mTab2 = (String) parent.getAdapter().getItem(selectedList.get(0));
                    if (mBean.getMaskNum()==2){
                        for (int i=0;i<mBean.getList().size();i++){
                            if (mTab2.equals(mBean.getList().get(i).getName())){
                                mTvPrice.setText("￥" + mBean.getList().get(i).getPrice());
                               mRealPrice = mBean.getList().get(i).getPrice();
                                String desc="";
                                if (TextUtils.isEmpty(mBean.getList().get(i).getStock())){
                                    desc="无货";
                                }
//                                else if(Integer.parseInt(mBean.getList().get(i).getStock())==0){
//                                    desc="无货";
//                                }
                                else{
                                    desc="有货";
                                }
                                mTvDesc.setText(desc);
                            }
                        }
                    }
                }
            }
        });

        mFlowTab3.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList.size() > 0) {
                    mTab3 = (String) parent.getAdapter().getItem(selectedList.get(0));
                }
                if (mBean.getMaskNum()==3){
                    for (int i=0;i<mBean.getList().size();i++){
                        if (mTab3.equals(mBean.getList().get(i).getName())){
                            mTvPrice.setText("￥" + mBean.getList().get(i).getPrice());
                            mRealPrice = mBean.getList().get(i).getPrice();
                            String desc="";
                            if (TextUtils.isEmpty(mBean.getList().get(i).getStock())){
                                desc="无货";
                            }
//                            else if(Integer.parseInt(mBean.getList().get(i).getStock())==0){
//                                desc="无货";
//                            }
                            else{
                                desc="有货";
                            }
                            mTvDesc.setText(desc);
                        }
                    }
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
        mTvPay.setOnClickListener(this);
        mTvAddCart.setOnClickListener(this);
    }

    private OnChoseListener mOnChoseListener;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_confirm) {
            if (mOnChoseListener != null) {
                if(mBean.getMaskNum()==3 && mTab1!=null && mTab2!=null && mTab3!=null){
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if(mBean.getMaskNum()==2 && mTab1!=null && mTab2!=null){
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()==1&& mTab1!=null){
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()!=0){
                    ToastUtils.showShort("请选择规格");
                }else {
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }

            }

        } else if (view.getId() == R.id.tv_add_car) {
            if (mOnChoseListener != null) {

                if(mBean.getMaskNum()>2 && mTab1!=null && mTab2!=null && mTab3!=null){
                    mOnChoseListener.select(true, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if(mBean.getMaskNum()==2 && mTab1!=null && mTab2!=null){
                    mOnChoseListener.select(true, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()==1&& mTab1!=null){
                    mOnChoseListener.select(true, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()!=0){
                    ToastUtils.showShort("请选择规格");
                }else {
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }

            }
        } else if (view.getId() == R.id.tv_pay) {

            if (mOnChoseListener != null) {
                if(mBean.getMaskNum()>2 && mTab1!=null && mTab2!=null && mTab3!=null){
                    mOnChoseListener.select(mIsAddCar, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if(mBean.getMaskNum()==2 && mTab1!=null && mTab2!=null){
                    mOnChoseListener.select(mIsAddCar, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()==1&& mTab1!=null){
                    mOnChoseListener.select(mIsAddCar, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3, mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }else if (mBean.getMaskNum()!=0){
                    ToastUtils.showShort("请选择规格");
                }else {
                    mOnChoseListener.select(false, mBean.getMaskName1(), mTab1, mBean.getMaskName2(), mTab2,mBean.getMaskName3(), mTab3,mCounterView.getInitNum() + "",mRealPrice);
                    dismiss();
                }
            }

        }



    }

    public interface OnChoseListener {
        void select(boolean isAddCar, String mask1, String tab1, String mask2, String tab2, String mask3, String tab3,String num,String realPrice);
    }

    public void setOnChoseListener(OnChoseListener onChoseListener) {
        mOnChoseListener = onChoseListener;
    }
}
