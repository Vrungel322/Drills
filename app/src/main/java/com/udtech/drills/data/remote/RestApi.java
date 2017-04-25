package com.udtech.drills.data.remote;

import com.udtech.drills.data.model.DrillsApi;
import com.udtech.drills.data.remote.login.User;
import okhttp3.ResponseBody;
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
}
