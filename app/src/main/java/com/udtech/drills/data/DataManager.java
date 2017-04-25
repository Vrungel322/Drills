package com.udtech.drills.data;

import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.remote.RestApi;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp.SignUpResetBody;
import okhttp3.Credentials;
import retrofit2.Response;
import rx.Observable;

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

  public Observable<Response<User>> login(String login, String password) {
    return mRestApi.login(Credentials.basic(login, password));
  }

  public Observable<Response<SignUpResetBody>> signUp(SignUpResetBody signUpResetBody) {
    return mRestApi.signUp(signUpResetBody);
  }

  public Observable<Response<SignUpResetBody>> resetPass(SignUpResetBody signUpResetBody) {
    return mRestApi.resetPass(signUpResetBody);
  }
}
