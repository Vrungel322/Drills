package com.udtech.drills.data.remote;

import com.udtech.drills.data.model.DrillsApi;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp.SignUpResetBody;
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

  public  Observable<Response<User>> login(String authentication) {
    return api.login(authentication);
  }

  public Observable<Response<SignUpResetBody>> signUp(SignUpResetBody signUpResetBody) {
    return api.signUp(signUpResetBody.getPhoneEmail());
  }

  public Observable<Response<SignUpResetBody>> resetPass(SignUpResetBody signUpResetBody) {
    return api.resetPass(signUpResetBody.getPhoneEmail());
  }
}
