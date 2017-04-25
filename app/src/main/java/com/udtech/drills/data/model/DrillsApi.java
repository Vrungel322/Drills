package com.udtech.drills.data.model;

import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp.SignUpResetBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

  @POST("api/web/v2/user/register") @FormUrlEncoded Observable<Response<SignUpResetBody>> signUp(
      @Field("phone_email") String signUpBody);

  @POST("api/web/v2/user/reset-password") @FormUrlEncoded
  Observable<Response<SignUpResetBody>> resetPass(@Field("phone_email") String phoneEmail);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
