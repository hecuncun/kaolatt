package com.jxbn.kaolatt.net;

import com.jxbn.kaolatt.base.BaseBean;
import com.jxbn.kaolatt.base.BaseNoDataBean;
import com.jxbn.kaolatt.bean.BannerInfoBean;
import com.jxbn.kaolatt.bean.FamousListBean;
import com.jxbn.kaolatt.bean.GoodListBean;
import com.jxbn.kaolatt.bean.GoodMoreListBean;
import com.jxbn.kaolatt.bean.GoodsMoreListBean;
import com.jxbn.kaolatt.bean.RegisterBean;
import com.jxbn.kaolatt.bean.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hecuncun on 2019/5/13
 */
public interface Api {

    /**
     *获取手机验证码
     * @param phone
     * @param type  1：注册，2：重置密码
     * @return
     */
    @POST("appTcmnPhoneCode/insertSelective")
    Observable<BaseBean<RegisterBean>> registerCodeCall(@Query("phone") String phone, @Query("type") int type);

    /**
     * 用户注册
     * @param phone
     * @param code
     * @param pwd
     * @return
     */
    @POST("appUserBase/insertSelective")
    Observable<BaseBean<UserInfoBean>> registerCall(@Query("phone") String phone, @Query("code") String code, @Query("pwd") String pwd);

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @POST("appUserBase/logoin")
    Observable<BaseBean<UserInfoBean>> loginCall(@Query("phone") String phone, @Query("pwd") String pwd);

    /**
     *重置密码
     * @param phone
     * @param code
     * @param pwd
     * @return
     */
    @POST("appUserBase/resetPwd")
    Observable<BaseNoDataBean> resetPwdCall(@Query("phone") String phone, @Query("code") String code, @Query("pwd") String pwd);

    /**
     * 首页轮播图
     * @return
     */
    @POST("appTcmnCarouselPicture/searchAll")
    Observable<BannerInfoBean> homeBannerCall();

    /**
     * 首页好物
     * @return
     */
    @POST("appGoodsInfo/selectHomeExquisiteGoods")
    Observable<GoodListBean> goodListCall();

    /**
     * 大牌
     * @return
     */
    @POST("appGoodsInfo/searchBigClassList")
    Observable<FamousListBean> famousListCall();

    /**
     * 更多推荐
     * @param page 当前页码，从1开始
     * @param type 1:综合排序，2：销量升降序，3：价格降序，4：价格升序，5：价格筛选
     * @return
     */
    @POST("appGoodsInfo/searchForPage")
    Observable<GoodsMoreListBean> goodsMoreListCall(@Query("page") int page, @Query("type") int type);

    /**
     * 更好好物
     * @param page
     * @return
     */

    @POST("appGoodsInfo/selectExquisiteGoods")
    Observable<GoodMoreListBean> goodMoreListCall(@Query("page") int page);

    /**
     *
     * @param page 当前页码，从1开始
     * @param bigClassId 大牌id
     * @param type 1:综合排序，2：销量升降序，3：价格降序，4：价格升序，5：价格筛选
     * @return
     */
    @POST("appGoodsInfo/searchForPage")
    Observable<GoodsMoreListBean> famousMoreListCall(@Query("page") int page, @Query("bigClassId") String bigClassId, @Query("type") int type);

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