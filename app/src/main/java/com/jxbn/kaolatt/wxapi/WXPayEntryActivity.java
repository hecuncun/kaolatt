package com.jxbn.kaolatt.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.jxbn.kaolatt.constants.Constant;
import com.jxbn.kaolatt.event.PayResultEvent;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

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

                EventBus.getDefault().post(new PayResultEvent(1));
                break;
            case -1:
                ToastUtils.showShort("支付失败");

                EventBus.getDefault().post(new PayResultEvent(2));
                break;
            case -2:
                ToastUtils.showShort("取消支付");

                EventBus.getDefault().post(new PayResultEvent(3));
                break;
            default:
        }
        finish();
    }
}