package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.jxbn.kaolatt.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * @author huqiang
 * @date 2018/7/10 10:17
 * @describe 底部彈出的dialog
 * @org shimi.com
 */

public class GoodsInfoBottomDialog extends BottomBaseDialog<GoodsInfoBottomDialog> {

    ListView listView;
    TextView tvCancel;

    //是否可以滑动
    private boolean isShowFour = true;
    private List<String> data;

    public GoodsInfoBottomDialog(Context context, View animateView, List<String> data) {
        super(context, animateView);
        this.data = data;
    }

    @Override
    public View onCreateView() {
        widthScale(1f);
//        showAnim(new Swing());
        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(mContext, R.layout.dialog_goods_info, null);
        listView =  inflate.findViewById(R.id.listView);
        tvCancel =  inflate.findViewById(R.id.tv_cancel);
        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#FFFFFF"), dp2px(5)));

        //绑定适配器
        listView.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_select_list, data) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                String str = item;
                viewHolder.setText(R.id.tv_content, str);
                ((TextView)(viewHolder.getView(R.id.tv_content))).setGravity(Gravity.LEFT);
            }
        });
        listView.setSelection(data.size() / 2);
        if (isShowFour) {
            //大于四项只显示四项，多余滚动
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            if (data.size() > 4) {
                params.height = SizeUtils.dp2px(200);
            }
            listView.setLayoutParams(params);
        }
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        listView.setOnItemClickListener(listener);
    }
}
