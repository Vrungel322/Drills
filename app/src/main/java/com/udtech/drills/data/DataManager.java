package com.udtech.drills.data;

import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.remote.RestApi;
import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.data.remote.send_user_data.SendUserDataEntity;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import java.util.ArrayList;
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

  public Observable<Response<Boolean>> sendUserData(String token) {
    ArrayList<HistoryForSend> historyForSends = new ArrayList<>();
    ArrayList<PracticForSend> practicForSends = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      historyForSends.add(
          new HistoryForSend(2, "history practice name " + i, 20, " " + i, "Dry Practice Drill",
              1491815605.812251 + i));
      practicForSends.add(
          new PracticForSend("name " + i, "0", 3, 1491815595.94008 + i, "description " + i, 1, 3,
              "0", " " + i, 3));
    }
    SendUserDataEntity sendUserDataEntity = new SendUserDataEntity(historyForSends, practicForSends);

    return mRestApi.sendUserData(sendUserDataEntity, token);
  }

  public void userLoggedIn() {
    mPref.setUserLogin();
  }

  public boolean isUserLogin(){
    return mPref.isUserLogin();
  }
}
