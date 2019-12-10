package com.jxbn.kaolatt.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jxbn.kaolatt.R;

/**
 * Created by heCunCun on 2019/12/10
 */
public class SortEditDialog extends FullScreenDialogFragment {

    private EditText mEtLow;
    private TextView mEtHigh;
    private TextView mTvConfirm;

    public static SortEditDialog newInstance() {
        SortEditDialog fragment = new SortEditDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.dialog_sort_edit, null, false);
        mEtLow = inflate.findViewById(R.id.et_low);
        mEtHigh = inflate.findViewById(R.id.et_high);
        mTvConfirm = inflate.findViewById(R.id.tv_confirm);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String low = mEtLow.getText().toString().trim();
                String high = mEtHigh.getText().toString().trim();
                ensureClickedListener.onEnsureClick(low,high);
                dismiss();
            }
        });
        return inflate;
    }

    //确定
    public interface OnEnsureClickedListener {
        void onEnsureClick(String low,String high);
    }

    private OnEnsureClickedListener ensureClickedListener;

    public SortEditDialog setOnEnsureClickedListener(OnEnsureClickedListener ensureClickedListener) {
        this.ensureClickedListener = ensureClickedListener;
        return this;
    }
}
