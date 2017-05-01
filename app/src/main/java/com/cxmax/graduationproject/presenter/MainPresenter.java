package com.cxmax.graduationproject.presenter;

import com.cxmax.graduationproject.base.RxPresenter;
import com.cxmax.graduationproject.component.RxBus.CommonSubscriber;
import com.cxmax.graduationproject.component.RxBus.RxBus;
import com.cxmax.graduationproject.component.RxBus.RxUtil;
import com.cxmax.graduationproject.model.event.NightModeEvent;
import com.cxmax.graduationproject.presenter.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
        registerEvent();
    }

    void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Function<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean apply(NightModeEvent nightModeEvent) {
                        return nightModeEvent.getNightMode();
                    }
                })
                .subscribeWith(new CommonSubscriber<Boolean>(view, "切换模式失败ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        view.useNightMode(aBoolean);
                    }
                })
        );
    }

}
