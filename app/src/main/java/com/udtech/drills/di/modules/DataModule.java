package com.udtech.drills.di.modules;

import android.content.Context;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.model.DrillsApi;
import com.udtech.drills.data.remote.RestApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;

/**
 * Created by Vrungel on 26.01.2017.
 */

@Module(includes = { RetrofitModule.class, DbModule.class }) public class DataModule {

  @Provides @Singleton DrillsApi provideSalonApi(Retrofit retrofit) {
    return retrofit.create(DrillsApi.class);
  }

  @Provides @Singleton RestApi provideRestApi(DrillsApi api) {
    return new RestApi(api);
  }

  @Provides @Singleton DataManager provideDataManager(RestApi restApi,
      PreferencesHelper preferencesHelper) {
    return new DataManager(restApi, preferencesHelper);
  }

  @Provides @Singleton PreferencesHelper providePreferencesHelper(Context context) {
    return new PreferencesHelper(context);
  }
}
