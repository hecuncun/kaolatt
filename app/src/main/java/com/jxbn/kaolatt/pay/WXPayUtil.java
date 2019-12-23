package com.jxbn.kaolatt.pay;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.jxbn.kaolatt.bean.PaySignBean;
import com.jxbn.kaolatt.constants.Constant;
import com.jxbn.kaolatt.net.CallbackListObserver;
import com.jxbn.kaolatt.net.SLMRetrofit;
import com.jxbn.kaolatt.net.ThreadSwitchTransformer;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import io.reactivex.Observable;

public class WXPayUtil {
    private Context context;
    private IWXAPI api;

    public WXPayUtil(Context context) {
        this.context = context;
        api = WXAPIFactory.createWXAPI(context, null);
        if (api.isWXAppInstalled()) {
            api.registerApp(Constant.WeiXinAppID);
        } else {
            ToastUtils.showShort("没有安装微信，请稍后");
        }

    }


    /**
     * 微信签名
     *
     */
    @SuppressWarnings("unused")
    public void WXpaySignature(String uid,String oid) {
        Observable<PaySignBean> paySignCall = SLMRetrofit.getInstance().getApi().paySignCall(uid, oid, 1);
        paySignCall.compose(new ThreadSwitchTransformer<PaySignBean>()).subscribe(new CallbackListObserver<PaySignBean>() {
            @Override
            protected void onSucceed(PaySignBean bean) {
                if (bean.getCode().equals(Constant.SUCCESSED_CODE)){
                    PayReq req = new PayReq();
                    req.appId = Constant.WeiXinAppID;
                    req.nonceStr = bean.getData().getNonceStr();
                    req.partnerId = Constant.WeiXinSHH;//天津候鸟微信材料商户号
                    req.timeStamp = bean.getData().getTimeStamp();
                    req.sign = bean.getData().getPaySign();
                    req.prepayId = bean.getData().getPrepayId();
                    req.packageValue = "Sign=WXPay";
                  //  req.extData = "" + payChannelId; // // 设置VIP支付标记
//                    api.sendReq(req);
                }else {
                    ToastUtils.showShort(bean.getMessage());
                }
            }

            @Override
            protected void onFailed() {

            }
        });


//        new NetRequest(context).WX_ORDER(orderId, payChannelId, new HttpGetPostCommonCallback() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onMessageRequest(String request) {
//                WXSignatureBean signatureBean = FastJsonUtils.getPerson(request, WXSignatureBean.class);
//                if (signatureBean != null) {
//                    PayReq req = new PayReq();
//                    req.appId = StringConfig.WeiXinAppID;
//                    req.nonceStr = signatureBean.getNonceStr();
//                    req.partnerId = StringConfig.WeiXinSHH;//天津候鸟微信材料商户号
//                    req.timeStamp = signatureBean.getTimeStamp();
//                    req.sign = signatureBean.getPaySign();
//                    req.prepayId = signatureBean.getPrepayId();
//                    req.packageValue = "Sign=WXPay";
//                    req.extData = "" + payChannelId; // // 设置VIP支付标记
//                    api.sendReq(req);
//                }
//            }
//
//            @Override
//            public void onMessageCode(String code) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }
}
