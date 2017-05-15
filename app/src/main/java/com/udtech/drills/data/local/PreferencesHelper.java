package com.udtech.drills.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by John on 26.01.2017.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "com.udtech.drills";

  private static final String IS_IN_SYSTEM = "IS_IN_SYSTEM";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }

  public void setUserLogin(String authKey) {
    mPreferences.edit().putString(IS_IN_SYSTEM, authKey).apply();
  }

  public String isUserLogin() {
    return mPreferences.getString(IS_IN_SYSTEM, "000");
  }
}
