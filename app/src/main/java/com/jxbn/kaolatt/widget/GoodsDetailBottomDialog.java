package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jxbn.kaolatt.R;


/**
 * Created by heCunCun on 2019/11/28
 */
public class GoodsDetailBottomDialog extends BottomSheetDialog implements View.OnClickListener {

//    private final TextView mTvCancel;
//    private final TextView mTvCamera;
//    private final TextView mTvPhotos;
    private final TextView mTvConfirm;

    public GoodsDetailBottomDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_goods_detail, null);

        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mTvConfirm.setOnClickListener(this);
//        mTvCamera = view.findViewById(R.id.tv_camera);
//        mTvCancel = view.findViewById(R.id.tv_cancel);
//        mTvPhotos.setOnClickListener(this);
//        mTvCamera.setOnClickListener(this);
//        mTvCancel.setOnClickListener(this);
        setContentView(view);
    }

    private OnChoseListener mOnChoseListener;

    @Override
    public void onClick(View view) {
//        if (mOnChoseListener!=null){
//            mOnChoseListener.select(view.getId());
//        }

        dismiss();

    }

   public interface OnChoseListener {
        void select(int resID);
    }

    public void setOnChoseListener(OnChoseListener onChoseListener) {
        mOnChoseListener = onChoseListener;
    }
}
