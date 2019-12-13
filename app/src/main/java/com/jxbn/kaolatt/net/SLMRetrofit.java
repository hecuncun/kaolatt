package com.jxbn.kaolatt.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jxbn.kaolatt.constants.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hecuncun on 2019/5/13
 */
public class SLMRetrofit {
    private static SLMRetrofit mService;
    private Api mApi;
    private static final int DEFAULT_TIME_OUT = 20;//超时时间
    private static final int DEFAULT_READ_TIME_OUT = 20;

    private SLMRetrofit() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(genericClient())
                .addConverterFactory(GsonConverterFactory.create(gson))  //数据的处理时，只有请求成功后，才能需要解析data的数据,其他时候我们直接抛异常处理 json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                .build();

        mApi = retrofit.create(Api.class);

    }

    private OkHttpClient genericClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();  //该拦截器用于记录应用中的网络请求的信息
        /**
         * 可以通过 setLevel 改变日志级别
         共包含四个级别：NONE、BASIC、HEADER、BODY

         NONE 不记录

         BASIC 请求/响应行
         --> POST /greeting HTTP/1.1 (3-byte body)
         <-- HTTP/1.1 200 OK (22ms, 6-byte body)

         HEADER 请求/响应行 + 头

         --> Host: example.com
         Content-Type: plain/text
         Content-Length: 3

         <-- HTTP/1.1 200 OK (22ms)
         Content-Type: plain/text
         Content-Length: 6

         BODY 请求/响应行 + 头 + 体
         */
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false) //失败后，是否重新连接，
        //启用Log日志
                .addInterceptor(loggingInterceptor)  //添加拦截器  拦截器拿到了request之后，可以对request进行重写，可以添加，移除，替换请求头，也能对response的header进行重写，改变response的body
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
//                        CacheControl.Builder builder = new CacheControl.Builder().maxAge(10, TimeUnit.MINUTES);
                        Request request = chain.request()
                                .newBuilder()
//                                .header("Cache-Control", builder.build().toString())
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Content-Type", "application/json; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Accept-Encoding", "gzip,sdch")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                //.addHeader("x-access-token", SpUtils.getString(Constant.HTTP_TOOKEN,""))
                                // .addHeader("Cookie", cookie)
                                //.addHeader("Authorization","APPCODE " + Constant.OCR_APP_CODE)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }


    public static SLMRetrofit getInstance() {
        if (mService == null) {
            synchronized (SLMRetrofit.class) {
                if (mService == null) {
                    mService = new SLMRetrofit();
                }
            }
        }
        return mService;
    }

    public static void resetInstance(){
        mService = null;
    }

    public Api getApi() {
        return mApi;
    }
}
