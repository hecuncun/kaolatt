package com.jxbn.kaolatt.utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by hesanwei on 2018/4/20
 */

public class ToJsonUtil {

    private static ToJsonUtil mToJsonUtil;
    private static Gson gson;


    public static ToJsonUtil getInstance(){
        if (mToJsonUtil == null){
            synchronized (ToJsonUtil.class){
                if (mToJsonUtil == null){
                    mToJsonUtil = new ToJsonUtil();
                    gson = new Gson();
                }
            }
        }

        return mToJsonUtil;
    }

    private ToJsonUtil(){

    }

    public String toJson(Object object){
        String json = gson.toJson(object);
         Log.e("sssss",json);
        return json;
    }
}
