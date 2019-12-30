package com.jxbn.kaolatt.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by hecuncun on 2018/4/17
 * 拨打电话
 */

public class CallPhoneUtil {

    public static void callPhone(Context context, String phone) {
        //隐式意图拨号
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);//拨号
        intent.setData(Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
