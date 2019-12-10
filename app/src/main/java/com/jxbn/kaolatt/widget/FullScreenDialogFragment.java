package com.jxbn.kaolatt.widget;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * description:全屏且背景透明的dialog
 */

public class FullScreenDialogFragment extends DialogFragment {

//    private static final int DEFAULT_ANIM = R.style.Down2TopAnimStyle;
//    private int animRes = R.style.Down2TopAnimStyle;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Window mWindow = getDialog().getWindow();
        mWindow.requestFeature(Window.FEATURE_NO_TITLE);
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onActivityCreated(savedInstanceState);
    }

//    boolean isAnim = true;
//
//    /**
//     * 是否开启动画
//     */
//    public void setAnim(boolean anim) {
//        isAnim = anim;
//    }
//
//    public void setAnim(boolean anim, @StyleRes int animRes) {
//        isAnim = anim;
//        this.animRes = animRes;
//    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置窗口透明
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//设置窗口全屏
        dialog.setCancelable(true);
//        if (isAnim) {
//            WindowManager.LayoutParams layoutParams = window.getAttributes();
//            layoutParams.windowAnimations = animRes == -1 ? DEFAULT_ANIM : animRes;
//            window.setAttributes(layoutParams);
//        }
        afterOnStart(dialog, window);
    }

    protected void afterOnStart(Dialog dialog, Window window) {
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception ignore) {
            //忽略
        }
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }
}
