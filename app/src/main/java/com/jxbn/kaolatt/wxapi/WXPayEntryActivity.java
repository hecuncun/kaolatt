package com.jxbn.kaolatt.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.jxbn.kaolatt.constants.Constant;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @class name：WXPayEntryActivity
 * @class describe: 微信回调页
 * @anthor Created by yangjun
 * @time 2017/12/1 下午3:58
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));

        api = WXAPIFactory.createWXAPI(this, Constant.WeiXinAppID);
        api.registerApp(Constant.WeiXinAppID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.e("WXPay","resp.errCode=="+resp.errCode);
        switch (resp.errCode) {
            case 0:
                ToastUtils.showShort("支付成功");
//                EventBusBean eventBusBean = new EventBusBean();
//                eventBusBean.setCode(IntConfig.PAY_SUCCESS);
//                eventBusBean.setStringTag("支付成功");
//                EventBus.getDefault().post(eventBusBean);
                break;
            case -1:
                ToastUtils.showShort("支付失败");
//                EventBusBean eventBusBeanError = new EventBusBean();
//                eventBusBeanError.setCode(IntConfig.PAY_FAILED);
//                eventBusBeanError.setStringTag("支付失败");
//                EventBus.getDefault().post(eventBusBeanError);
                break;
            case -2:
                ToastUtils.showShort("取消支付");
//                EventBusBean eventBusBeanCancel = new EventBusBean();
//                eventBusBeanCancel.setCode(IntConfig.PAY_FAILED);
//                eventBusBeanCancel.setStringTag("取消支付");
//                EventBus.getDefault().post(eventBusBeanCancel);
                break;
            default:
        }
        finish();
    }
}