package com.udtech.drills.data;

import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.remote.RestApi;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class DataManager {

  private RestApi mRestApi;
  private PreferencesHelper mPref;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
  }


}
