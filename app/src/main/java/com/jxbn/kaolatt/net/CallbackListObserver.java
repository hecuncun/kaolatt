package com.jxbn.kaolatt.net;


import android.support.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * api请求的结果回调
 * data 里面是一个数组的解析回调
 *
 * 在Java中通过Observable类和Observer接口实现了观察者模式。一个Observer对象监视着一个Observable对象的变化，当Observable对象发生变化时，Observer得到通知，就可以进行相应的工作
 */

public abstract class CallbackListObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart();
    }

    @Override
    public void onNext(T bean){
        onSucceed(bean);
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
    protected abstract void onSucceed(T t);


    /**
     * 请求异常
     */
    protected void onException(Throwable t) {
//        DialogUtil.showLoadingDialog(HzlcApplication.getContext());
    }

    /**
     * 请求错误
     */
    protected abstract void onFailed();

}
