package com.jxbn.kaolatt.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.ToastUtils;
import com.jxbn.kaolatt.constants.Constant;
import com.jxbn.kaolatt.event.PayResultEvent;
import com.jxbn.kaolatt.net.CallbackListObserver;
import com.jxbn.kaolatt.net.SLMRetrofit;
import com.jxbn.kaolatt.net.ThreadSwitchTransformer;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;


/**
 * @author huqiang
 * @date 2018/7/21
 * @describe 阿里支付工具类--新接口
 * @org 十米科技(shimi.com)
 */
public class AlipayUtils {
    private static final int SDK_PAY_FLAG = 1;
    private Context context;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showShort("支付成功");
                        EventBus.getDefault().post(new PayResultEvent(1));
                    } else {
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.showShort("充值结果确认中成功");
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            ToastUtils.showShort("取消支付");
                            EventBus.getDefault().post(new PayResultEvent(3));
                        } else {
                            ToastUtils.showShort("支付失败");
                            EventBus.getDefault().post(new PayResultEvent(2));
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 支付宝
     *
     * @param context   上下文
     */
    public AlipayUtils(final Context context, final String oid, String uid) {
        this.context = context;
        PayTreasure(uid, oid);
    }


//    /**
//     * create the order info. 创建订单信息
//     */
//
//    private String getOrderInfo(AliPaySign result) {
//        // 参数编码， 固定值
//        String orderInfo = "_input_charset=\"utf-8\"";
//        // 商品详情
//        orderInfo += "&body=" + "\"" + result.getBody() + "\"";
//        // 设置未付款交易的超时时间
//        orderInfo += "&it_b_pay=\"30m\"";
//        // 服务器异步通知页面路径
//        orderInfo += "&notify_url=" + "\"" + result.getNotify_url() + "\"";
//        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + result.getOut_trade_no() + "\"";
//        // 签约合作者身份ID
//        orderInfo += "&partner=" + "\"" + result.getPartner() + "\"";
//        // 支付类型， 固定值
//        orderInfo += "&payment_type=\"" + result.getPayment_type() + "\"";
//        // 签约卖家支付宝账号
//        orderInfo += "&seller_id=" + "\"" + result.getSeller_id() + "\"";
//        // 服务接口名称， 固定值
//        orderInfo += "&service=\"mobile.securitypay.pay\"";
//        // 商品名称
//        orderInfo += "&subject=" + "\"" + result.getSubject() + "\"";
//        // 商品金额
//        orderInfo += "&total_fee=" + "\"" + result.getTotal_fee() + "\"";
//        return orderInfo;
//   }
//
//    private String getSignType() {
//        return "sign_type=\"RSA2\"";
//    }

    /**
     * 调用支付宝
     */
    private void PayTreasure(String uid,String oid) {
        Observable<AliPaySignBean> wxPaySignCall = SLMRetrofit.getInstance().getApi().aliPaySignCall(uid, oid, 2);
        wxPaySignCall.compose(new ThreadSwitchTransformer<AliPaySignBean>()).subscribe(new CallbackListObserver<AliPaySignBean>() {
            @Override
            protected void onSucceed(AliPaySignBean bean) {
                if (bean.getCode().equals(Constant.SUCCESSED_CODE)){
                    toPay(bean.getData().getSign());
                }else {
                    ToastUtils.showShort(bean.getMessage());
                }
            }

            @Override
            protected void onFailed() {

            }
        });
//        new NetRequest(context).AL_ORDER(order, type, new HttpGetPostCommonCallback() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onMessageRequest(String request) {
//                if (!StringUtils.isEmpty(request)) {
//                    toPay(request);
//                } else {
//                    ToastUtils.showShort("支付宝签名失败");
//                }
//            }
//
//            @Override
//            public void onMessageCode(String code) {
//                ToastUtils.showShort(ErrorCode.getErrorMsg(code));
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }

    /**
     * 吊起支付宝支付
     *
     * @param response
     */
    private void toPay(String response) {
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String orderInfo = response;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) context);
                // 调用支付接口，获取支付结果
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
