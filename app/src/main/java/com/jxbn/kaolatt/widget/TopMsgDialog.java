package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.widget.base.TopBaseDialog;
import com.jxbn.kaolatt.R;
import com.jxbn.kaolatt.bean.MsgListBean;
import com.jxbn.kaolatt.constants.Constant;
import com.jxbn.kaolatt.glide.GlideUtils;

/**
 * Created by hecuncun on 2019/12/17
 */
public class TopMsgDialog extends TopBaseDialog<TopMsgDialog> {
    private MsgListBean.DataBean.RowsBean mBean;
    private ImageView mIvClose;

    public TopMsgDialog(Context context, MsgListBean.DataBean.RowsBean bean) {
        super(context);
        mBean=bean;
    }

    @Override
    public View onCreateView() {
        widthScale(0.9f);
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_top_msg, null);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvContent = inflate.findViewById(R.id.tv_content);
        ImageView ivPic = inflate.findViewById(R.id.iv_img);
        mIvClose = inflate.findViewById(R.id.iv_close);
        tvTitle.setText(mBean.getTitle());
        tvContent.setText(mBean.getTitle());
        GlideUtils.showRound(ivPic, Constant.BASE_URL+mBean.getPhoto(),R.drawable.ic_launcher_background,6);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
