package com.jxbn.kaolatt.net;

import com.jxbn.kaolatt.base.BaseBean;
import com.jxbn.kaolatt.base.BaseNoDataBean;
import com.jxbn.kaolatt.bean.BannerInfoBean;
import com.jxbn.kaolatt.bean.CouponListBean;
import com.jxbn.kaolatt.bean.FamousListBean;
import com.jxbn.kaolatt.bean.GoodListBean;
import com.jxbn.kaolatt.bean.GoodMoreListBean;
import com.jxbn.kaolatt.bean.GoodsDetailBean;
import com.jxbn.kaolatt.bean.GoodsMoreListBean;
import com.jxbn.kaolatt.bean.HotTagListBean;
import com.jxbn.kaolatt.bean.ImgBean;
import com.jxbn.kaolatt.bean.MsgListBean;
import com.jxbn.kaolatt.bean.MyCollectionListBean;
import com.jxbn.kaolatt.bean.RegisterBean;
import com.jxbn.kaolatt.bean.ScoreListBean;
import com.jxbn.kaolatt.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by hecuncun on 2019/5/13
 */
public interface Api {

    /**
     * 获取手机验证码
     *
     * @param phone
     * @param type  1：注册，2：重置密码
     * @return
     */
    @POST("appTcmnPhoneCode/insertSelective")
    Observable<BaseBean<RegisterBean>> registerCodeCall(@Query("phone") String phone, @Query("type") int type);

    /**
     * 用户注册
     *
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
     * 重置密码
     *
     * @param phone
     * @param code
     * @param pwd
     * @return
     */
    @POST("appUserBase/resetPwd")
    Observable<BaseNoDataBean> resetPwdCall(@Query("phone") String phone, @Query("code") String code, @Query("pwd") String pwd);

    /**
     * 首页轮播图
     *
     * @return
     */
    @POST("appTcmnCarouselPicture/searchAll")
    Observable<BannerInfoBean> homeBannerCall();

    /**
     * 首页好物
     *
     * @return
     */
    @POST("appGoodsInfo/selectHomeExquisiteGoods")
    Observable<GoodListBean> goodListCall();

    /**
     * 大牌
     *
     * @return
     */
    @POST("appGoodsInfo/searchBigClassList")
    Observable<FamousListBean> famousListCall();

    /**
     * 更多推荐
     *
     * @param page 当前页码，从1开始
     * @param type 1:综合排序，2：销量升降序，3：价格降序，4：价格升序，5：价格筛选
     * @return
     */
    @POST("appGoodsInfo/searchForPage")
    Observable<GoodsMoreListBean> goodsMoreListCall(@Query("page") int page, @Query("type") int type);

    /**
     * 更好好物
     *
     * @param page
     * @return
     */

    @POST("appGoodsInfo/selectExquisiteGoods")
    Observable<GoodMoreListBean> goodMoreListCall(@Query("page") int page);

    /**
     * @param page       当前页码，从1开始
     * @param bigClassId 大牌id
     * @param type       1:综合排序，2：销量升降序，3：价格降序，4：价格升序，5：价格筛选
     * @return
     */
    @POST("appGoodsInfo/searchForPage")
    Observable<GoodsMoreListBean> famousMoreListCall(@Query("page") int page, @Query("bigClassId") String bigClassId, @Query("type") int type);

    /**
     * 未读消息数
     *
     * @param uid
     * @return
     */
    @POST("appActiveMessage/searchCount")
    Observable<BaseNoDataBean> unReadMsgCall(@Query("uid") String uid);

    /**
     * 消息列表
     *
     * @param page
     * @param uid
     * @return
     */
    @POST("appActiveMessage/searchForPage")
    Observable<MsgListBean> msgListCall(@Query("page") int page, @Query("uid") String uid);

    /**
     * 更新消息状态
     *
     * @param uid
     * @param mid
     * @return
     */
    @POST("appActiveMessage/updateById")
    Observable<BaseNoDataBean> updateMsgStateCall(@Query("uid") String uid, @Query("mid") String mid);

    /**
     * 热门搜索
     */
    @POST("appGoodsHot/searchAll")
    Observable<HotTagListBean> hotTagCall();


    /**
     * 搜索页多条件搜索
     *
     * @param page       当前页码，从1开始
     * @param bigClassId 大牌id
     * @param type       1:综合排序，2：销量升降序，3：价格降序，4：价格升序，5：价格筛选
     * @return
     */
    @POST("appGoodsInfo/searchForPage")
    Observable<GoodsMoreListBean> searchListCall(@Query("page") int page, @Query("name") String name, @Query("type") int type, @Query("max") Double max, @Query("min") Double min);

    /**
     * 积分列表
     *
     * @param page
     * @param uid
     * @return
     */
    @POST("appUserIntegral/searchForPage")
    Observable<ScoreListBean> scoreListCall(@Query("page") int page, @Query("uid") String uid);

    /**
     * 反馈
     * @param uid
     * @param content
     * @return
     */
    @POST("appTcmnFeedback/insertSelective")
    Observable<BaseNoDataBean> feedBackCall(@Query("uid") String uid, @Query("content") String content);

    /**
     * 优惠券
     * @param uid
     * @param type 类型：1：所有，2：未使用
     * @return
     */
    @POST("appUserCardUse/searchAll")
    Observable<CouponListBean> couponListCall(@Query("uid")String uid,@Query("type") int type);

    /**
     * 上传图片
     * @param file
     * @return
     */
    @POST("appimg/upload")
    @Multipart
    Observable<BaseBean<ImgBean>> uploadCall(@Part MultipartBody.Part file);

    /**
     * 修改用户信息
     * @param uid
     * @param nickName
     * @param picture
     * @return
     */
    @POST("appUserBase/updateById")
    Observable<BaseNoDataBean> updateInfoCall(@Query("uid") String uid,@Query("nickName") String nickName,@Query("picture") String picture);

    /**
     * 我的收藏
     * @param uid
     * @return
     */
    @POST("appGoodsInfo/selectCollectGoods")
    Observable<MyCollectionListBean> myCollectionListCall(@Query("uid") String uid);

    /**
     * 批量取消收藏
     * @param gids
     * @param uid
     * @return
     */
    @POST("appGoodsInfo/batchCancellCollectGoods")
    Observable<BaseNoDataBean> deleteCollection(@Query("gids") String gids,@Query("uid") String uid);

    /**
     * 商品详情
     * @param gid
     * @return
     */
    @POST("appGoodsInfo/selectDetail")
    Observable<GoodsDetailBean> goodsDetailCall(@Query("gid") String gid);
//    /**
//     * 获取全员信息e
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