package com.cxmax.graduationproject.di.component;

import android.app.Activity;

import com.cxmax.graduationproject.di.module.FragmentModule;
import com.cxmax.graduationproject.di.scope.FragmentScope;
import com.cxmax.graduationproject.ui.main.fragment.IndexFragment;
import com.cxmax.graduationproject.ui.main.fragment.SettingFragment;

import dagger.Component;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(IndexFragment indexFragment);

    void inject(SettingFragment settingFragment);
}
