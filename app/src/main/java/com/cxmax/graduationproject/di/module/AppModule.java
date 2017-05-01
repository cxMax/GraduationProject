package com.cxmax.graduationproject.di.module;

import com.cxmax.graduationproject.app.App;
import com.cxmax.graduationproject.model.db.RealmHelper;

import javax.inject.Singleton;

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
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }


    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }
}
