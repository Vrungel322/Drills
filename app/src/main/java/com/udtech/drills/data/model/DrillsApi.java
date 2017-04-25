package com.udtech.drills.data.model;

import com.udtech.drills.data.remote.login.User;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.OPTIONS;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Vrungel on 26.01.2017.
 */

public interface DrillsApi {
  @OPTIONS("api/web/v2/user/login") Observable<Response<User>> login(
      @Header("Authorization") String authentication);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
