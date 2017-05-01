package com.cxmax.graduationproject.di.component;

import android.app.Activity;

import com.cxmax.graduationproject.ui.main.activity.MainActivity;
import com.cxmax.graduationproject.di.module.ActivityModule;
import com.cxmax.graduationproject.di.scope.ActivityScope;

import dagger.Component;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
