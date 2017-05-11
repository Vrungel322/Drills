package com.udtech.drills.data.model;

import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.OPTIONS;
import retrofit2.http.POST;
import retrofit2.http.Query;
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

  @GET("/api/web/v2/practic-history") Observable<Response<UserDataEntity>> fetchUserData(
      @Query("access-token") String token);

  @POST("/api/web/v2/practic-history") @FormUrlEncoded
  Observable<Response<Boolean>> sendUserDataPractic(@Query("access-token") String token,
      @Field("practic") String practicForSends);

  @POST("/api/web/v2/practic-history") @FormUrlEncoded
  Observable<Response<Boolean>> sendUserDataHistory(@Query("access-token") String token,
      @Field("history") String historyForSends);

  //@POST("signin") Observable<TokenEntity> login(
  //    @Body LoginBody credentials
  //);
}
