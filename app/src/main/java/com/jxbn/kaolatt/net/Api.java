package com.jxbn.kaolatt.net;

import com.jxbn.kaolatt.base.BaseBean;
import com.jxbn.kaolatt.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hecuncun on 2019/5/13
 */
public interface Api {
    /**
     * 登录接口
     * @param loginName
     * @param password
     * @return
     */
    @GET("security/login")
    Observable<BaseBean<UserInfo>> loginCall(@Query("loginName") String loginName, @Query("password") String password);

//    /**
//     * 获取全员信息
//     * @return
//     */
//    @GET("security/user")
//    Observable<AllPersonInfoBean> getAllPersonCall();
//
//    /**
//     * 手持机与手表绑定接口
//     *
//     * @param mac         手持机mac号
//     * @param childNumber 手表的固话注册码
//     * @return
//     */
//    @POST("handsets/binding/{handsetNumber}-{childNumber}")
//    Observable<BaseBean> canBinding(@Path("handsetNumber") String mac, @Path("childNumber") String childNumber);
//
//    /**
//     * 手持机与手表解绑接口
//     *
//     * @param mac         手持机的mac号
//     * @param childNumber 手表的固话注册码   如果是多个之间用,隔开
//     * @return
//     */
//    @DELETE("handsets/binding/{handsetNumber}-{childNumber}")
//    Observable<BaseBean> deleteBinding(@Path("handsetNumber") String mac, @Path("childNumber") String childNumber);
//
//    /**
//     * 获取所有的北斗信息
//     *
//     * @return
//     */
//    @GET("bds")
//    Observable<AllBDInfosBean> getAllBDInfos();
//
//    /**
//     * 4g 通信接口
//     */
//
//    @POST("remoteapi/bd")
//    Observable<NetResponseBean> uploadInfo(@Body RequestBody requestBody);
//
//    /**
//     * 根据手持机的mac号  查询绑定的手表
//     */
//    @GET("handsets/binding/{handsetNumber}/watch")
//    Observable<BindingWatchBean> getBindingWatch(@Path("handsetNumber") String mac);
//
//    /**
//     * mac与北斗上传绑定关系的接口
//     */
//
//    @PUT("handsets/binding/bd/{handsetNumber}-{bdNumber}")
//    Observable<BaseBean> uploadMacAndBdNum(@Path("handsetNumber") String mac, @Path("bdNumber") String bdNumber);
}