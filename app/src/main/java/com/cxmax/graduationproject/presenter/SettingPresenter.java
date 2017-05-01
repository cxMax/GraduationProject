package com.cxmax.graduationproject.presenter;

import com.cxmax.graduationproject.base.RxPresenter;
import com.cxmax.graduationproject.presenter.contract.SettingContract;

import javax.inject.Inject;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/5/1.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    @Inject
    public SettingPresenter() {
    }

}
