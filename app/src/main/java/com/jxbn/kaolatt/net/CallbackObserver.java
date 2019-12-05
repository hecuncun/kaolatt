package com.jxbn.kaolatt.net;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.jxbn.kaolatt.base.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * api请求的结果回调
 * data 是一个bean对象的解析回调
 */

public abstract class CallbackObserver<T> implements Observer<BaseBean<T>> {


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart();
    }

    @Override
    public void onNext(BaseBean<T> tBaseResultBean) {
        try {

            if (tBaseResultBean.isSuccessed()) {
                T t = tBaseResultBean.getData();
                onSucceed(t,tBaseResultBean.getMessage());
            } else {

                if (!TextUtils.isEmpty(tBaseResultBean.getMessage())){
                   //showToast(tBaseResultBean.getMessage());
                }
                onFailed();
            }

        } catch (Exception e) {
            Log.e("exception",e.getLocalizedMessage());
          //  ToastUtil.showToast(e.getLocalizedMessage());
            onFailed();
        }

    }

    @Override
    public void onError(Throwable t) {
       // ToastUtil.showToast(t.getLocalizedMessage());
        onFailed();
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求开始
     */
    protected void onStart() {

    }

    /**
     * 请求成功
     */
    protected abstract void onSucceed(T t, String desc);


    /**
     * 请求异常
     */
    protected void onException(Throwable t) {

    }

    /**
     * 请求错误
     */
    protected abstract void onFailed();

}
