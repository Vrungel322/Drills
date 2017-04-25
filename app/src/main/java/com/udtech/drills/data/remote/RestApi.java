package com.udtech.drills.data.remote;

import com.udtech.drills.data.model.DrillsApi;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp.SignUpBody;
import retrofit2.Response;
import rx.Observable;
import timber.log.Timber;

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

  public Observable<Response<SignUpBody>> signUp(SignUpBody signUpBody) {
    return api.signUp(signUpBody.getPhoneEmail());
  }
}
