package com.udtech.drills.data.remote;

import com.udtech.drills.data.model.DrillsApi;
import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public class RestApi {
  private final DrillsApi api;

  public RestApi(DrillsApi api) {
    this.api = api;
  }

  public Observable<Response<User>> login(String authentication) {
    return api.login(authentication);
  }

  public Observable<Response<SignUpResetBody>> signUp(SignUpResetBody signUpResetBody) {
    return api.signUp(signUpResetBody.getPhoneEmail());
  }

  public Observable<Response<SignUpResetBody>> resetPass(SignUpResetBody signUpResetBody) {
    return api.resetPass(signUpResetBody.getPhoneEmail());
  }

  public Observable<Response<UserDataEntity>> fetchUserData(String token) {
    return api.fetchUserData(token);
  }

  public Observable<Response<Boolean>> sendUserDataPractic(String practicForSend, String token) {
    return api.sendUserDataPractic(token, practicForSend);
  }

  public Observable<Response<Boolean>> sendUserDataHistory(String historyForSend, String token) {
    return api.sendUserDataPractic(token, historyForSend);
  }
}
