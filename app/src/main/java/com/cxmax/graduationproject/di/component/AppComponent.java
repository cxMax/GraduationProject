package com.cxmax.graduationproject.di.component;

import com.cxmax.graduationproject.app.App;
import com.cxmax.graduationproject.base.BasePresenter;
import com.cxmax.graduationproject.di.module.AppModule;
import com.cxmax.graduationproject.model.db.RealmHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    App getContext();

    RealmHelper realmHelper();

}
