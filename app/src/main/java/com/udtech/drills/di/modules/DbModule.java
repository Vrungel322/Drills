package com.udtech.drills.di.modules;

import android.content.Context;
import com.udtech.drills.data.local.Db.DBHelper;
import com.udtech.drills.data.local.Db.HistoryForSendHelper;
import com.udtech.drills.data.local.Db.PracticHelper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 15.05.2017.
 */

@Module public class DbModule {

  @Provides @Singleton public DBHelper provideDBHelper(Context context) {
    return new DBHelper(context);
  }

  @Provides @Singleton public PracticHelper provideContactHelper(DBHelper helper) {
    return new PracticHelper(helper);
  }

  @Provides @Singleton public HistoryForSendHelper provideHistoryForSendHelper(DBHelper helper) {
    return new HistoryForSendHelper(helper);
  }
}

