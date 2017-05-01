package com.cxmax.graduationproject.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.cxmax.graduationproject.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-4-30.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
