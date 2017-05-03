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
import java.util.List;
import okhttp3.Credentials;
import retrofit2.Response;
import rx.Observable;
import timber.log.Timber;

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

  public Observable<Response<Boolean>> sendUserData(String token,
      List<PracticForSend> practicForSends) {
    ArrayList<HistoryForSend> historyForSends = new ArrayList<>();
    //ArrayList<PracticForSend> practicForSends = new ArrayList<>();
    //for (int i = 0; i < 5; i++) {
      historyForSends.add(new HistoryForSend(1, "history practice name 222", 3,
          "0RZbEJSWOZnohWyIxS8fr9F5iEYAdu", "Dry Practice Drill", 1491815605.812252));
      //practicForSends.add(
      //    new PracticForSend("name " + i, "0", 3, 1491815595.94008 + i, "description " + i, 1, 3,
      //        "0", " " + i, 3));
    //}
    Timber.e(practicForSends.get(0).getDryPracticsName());
    SendUserDataEntity sendUserDataEntity =
        new SendUserDataEntity(historyForSends, practicForSends);

    return mRestApi.sendUserData(sendUserDataEntity, "dE-d3FTq5cJ8-auV-1ccViR9cWLw7yz1");
  }

  public void userLoggedIn() {
    mPref.setUserLogin();
  }

  public boolean isUserLogin() {
    return mPref.isUserLogin();
  }
}
