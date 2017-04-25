package com.udtech.drills;

import android.app.Application;
import com.udtech.drills.di.components.AppComponent;
import com.udtech.drills.di.components.DaggerAppComponent;
import com.udtech.drills.di.modules.AppModule;
import shortbread.Shortbread;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.01.2017.
 */

public class App extends Application {

  private static AppComponent sAppComponent;

  public static AppComponent getAppComponent() {
    return sAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();

    Shortbread.create(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}
