package com.jxbn.kaolatt.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

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
    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * 得到json文件中的内容
     * @param context
     * @param fileName
     * @return
     */
    public static String getAsstesJson(Context context, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
