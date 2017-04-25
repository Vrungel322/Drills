package com.udtech.drills.di.modules;

import android.app.Application;
import android.content.Context;
import com.udtech.drills.base.Navigator;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.utils.RxBus;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */

@Module(includes = { DataModule.class }) public class AppModule {

  private final Application mApplication;

  public AppModule(Application application) {
    mApplication = application;
  }

  @Provides @Singleton Context provideAppContext() {
    return mApplication;
  }

  @Provides @Singleton public RxBus provideRxBus() {
    return new RxBus();
  }

  @Provides @Singleton public Navigator provideNavigator() {
    return new Navigator();
  }

  @Provides @Singleton public User provideUser(){return new User();}
}
