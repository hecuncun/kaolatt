package com.jxbn.kaolatt.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxbn.kaolatt.R;
import com.jxbn.kaolatt.glide.GlideUtils;
import com.jxbn.kaolatt.utils.CallPhoneUtil;

/**
 * Created by heCunCun on 2019/12/10
 */
public class ServiceDialog  extends FullScreenDialogFragment{

    private TextView mTvQQ;
    private TextView mTvPhone;
    private ImageView mIvWx;
    private RelativeLayout rlPhone;

    private static String mPhone;

    public static ServiceDialog newInstance(String qq, String phone, String wxCodeUrl){
        ServiceDialog fragment=new ServiceDialog();
        Bundle args = new Bundle();
        args.putString("qq", qq);
        args.putString("phone", phone);
        args.putString("wxCodeUrl", wxCodeUrl);
        mPhone=phone;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.dialog_service, null, false);
        mTvPhone = inflate.findViewById(R.id.tv_phone);
        mTvQQ = inflate.findViewById(R.id.tv_qq);
        mIvWx = inflate.findViewById(R.id.iv_wx);
        rlPhone = inflate.findViewById(R.id.rl_phone);
        rlPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallPhoneUtil.callPhone(getActivity(),mPhone);
            }
        });
        if (getArguments()!=null){
            mTvPhone.setText(getArguments().getString("phone"));
            mTvQQ.setText(getArguments().getString("qq"));
            GlideUtils.showAnimation(mIvWx,getArguments().getString("wxCodeUrl"),R.mipmap.wx_code);
        }
        return inflate;
    }

}
