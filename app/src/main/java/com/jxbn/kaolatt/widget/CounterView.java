package com.jxbn.kaolatt.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jxbn.kaolatt.R;


/**
 * Created by heCunCun on 2019/11/26
 */
public class CounterView extends LinearLayout {

    private TextView mTvNum;
    private TextView mTvAdd;
    private TextView mTvMinus;

    public int getInitNum() {
        return mInitNum;
    }

    public void setInitNum(int initNum) {
        mInitNum = initNum;
    }

    private int mInitNum;
    private int mUpLimit;
    private int mDownLimit;

    public CounterView(Context context) {
        this(context,null);
    }

    public CounterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context,attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_counter_view, this);
        mTvNum = view.findViewById(R.id.tv_num);
        mTvNum.setText(String.valueOf(mInitNum));
        mTvAdd = view.findViewById(R.id.tv_add);
        mTvMinus = view.findViewById(R.id.tv_minus);
        initListener();

    }

    private void initListener() {
        mTvAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInitNum++;
                if (mInitNum>mUpLimit){
                    mInitNum=mUpLimit;
                }
                mTvNum.setText(String.valueOf(mInitNum));
            }
        });

        mTvMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInitNum--;
                if (mInitNum<mDownLimit){
                    mInitNum=mDownLimit;
                }
                mTvNum.setText(String.valueOf(mInitNum));
            }
        });
    }

    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CounterView);
        mInitNum = typedArray.getInt(R.styleable.CounterView_init_num, 1);
        mUpLimit = typedArray.getInt(R.styleable.CounterView_up_limit, 10000);
        mDownLimit = typedArray.getInt(R.styleable.CounterView_down_limit, 0);
        typedArray.recycle();
    }




}
