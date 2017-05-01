package com.cxmax.graduationproject.component.RxBus;

import android.text.TextUtils;

import com.cxmax.graduationproject.base.BaseView;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView view;
    private String errorMsg;

    protected CommonSubscriber(BaseView view){
        this.view = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg){
        this.view = view;
        this.errorMsg = errorMsg;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (view == null)
            return;
        if (errorMsg != null && !TextUtils.isEmpty(errorMsg)) {
            view.showError(errorMsg);
        }  else {
            view.showError("未知错误ヽ(≧Д≦)ノ");
        }
    }
}
