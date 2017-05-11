package com.udtech.drills.data;

import com.google.gson.Gson;
import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.remote.RestApi;
import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import java.util.ArrayList;
import java.util.List;
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

  public Observable<Response<UserDataEntity>> fetchUserData(String token) {
    return mRestApi.fetchUserData(token);
  }

  public Observable<Response<Boolean>> sendUserDataPractic(String token,
      List<PracticForSend> practicForSends) {
    String json = new Gson().toJson(practicForSends);
    return mRestApi.sendUserDataPractic(json, token);
  }

  public Observable<Response<Boolean>> sendUserDataHistory(String token,
      List<PracticForSend> practicForSends) {
    ArrayList<HistoryForSend> historyForSends = new ArrayList<>();
    historyForSends.add(new HistoryForSend(1, "Dry 111111222", 3, "0RZbEJSWOZnohWyIxS8fr9F5iEYAdu",
        "Dry Practice Drill", 1491815605.812252));

    String json = new Gson().toJson(historyForSends);

    return mRestApi.sendUserDataPractic(json, token);
  }

  public void userLoggedIn() {
    mPref.setUserLogin();
  }

  public boolean isUserLogin() {
    return mPref.isUserLogin();
  }
}
