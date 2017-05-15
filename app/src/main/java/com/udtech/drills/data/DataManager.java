package com.udtech.drills.data;

import com.google.gson.Gson;
import com.udtech.drills.data.local.Db.PracticHelper;
import com.udtech.drills.data.local.PreferencesHelper;
import com.udtech.drills.data.local.mappers.PracticForSendToPracticMapper;
import com.udtech.drills.data.local.mappers.PracticToPracticForSendMapper;
import com.udtech.drills.data.remote.RestApi;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import com.udtech.drills.utils.Converters;
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
  private PracticHelper mPracticHelper;
  private int mTotalTime;

  public DataManager(RestApi restApi, PreferencesHelper preferencesHelper,
      PracticHelper practicHelper) {
    this.mRestApi = restApi;
    this.mPref = preferencesHelper;
    this.mPracticHelper = practicHelper;
  }

  public Observable<Response<User>> login(String login, String password) {
    // TODO: 15.05.2017 romove dropping
    //mPracticHelper.dropTableAndCreate();
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

  public Observable<Response<Boolean>> sendUserDataPractic(String token) {
    List<PracticForSend> practicForSends = new ArrayList<>();
    return mPracticHelper.getAllPractics().concatMap(practics -> {
      for (int i = 0; i < practics.size(); i++) {
        practicForSends.add(new PracticToPracticForSendMapper().transform(practics.get(i)));
      }
      String json = new Gson().toJson(practicForSends);
      return mRestApi.sendUserDataPractic(json, token);
    });
  }

  public Observable<Response<Boolean>> sendUserDataHistory(String token,
      List<PracticForSend> practicForSends) {
    ArrayList<HistoryForSend> historyForSends = new ArrayList<>();
    historyForSends.add(new HistoryForSend(1, "Dry 111111222", 3, "0RZbEJSWOZnohWyIxS8fr9F5iEYAdu",
        "Dry Practice Drill", 1491815605.812252));

    String json = new Gson().toJson(historyForSends);

    return mRestApi.sendUserDataPractic(json, token);
  }

  public void userLoggedIn(String authKey) {
    mPref.setUserLogin(authKey);
  }

  public String getAuthIfLogin() {
    return mPref.isUserLogin();
  }

  public Observable<Boolean> putUserDataEntityToDb(UserDataEntity userDataEntity) {
    for (int i = 0; i < userDataEntity.getPractic().size(); i++) {
      mPracticHelper.insert(userDataEntity.getPractic().get(i));
    }
    return Observable.just(true);
  }

  public Observable<Boolean> putPracticToDb(List<PracticForSend> practicForSends) {
    for (int i = 0; i < practicForSends.size(); i++) {
      mPracticHelper.insert(new PracticForSendToPracticMapper().transform(practicForSends.get(i)));
    }
    return Observable.just(true);
  }

  public Observable<List<Practic>> getPracticsFromDb() {
    return mPracticHelper.getAllPractics();
  }

  public Observable<String> getTotalSetsTime() {
    mTotalTime = 0;
    return mPracticHelper.getAllPractics().concatMap(Observable::from).concatMap(practic -> {
      mTotalTime = (int) (mTotalTime + Math.round(practic.getDryPracticsTime()));
      Timber.e(String.valueOf(mTotalTime));
      return Observable.just(Converters.timeFromSeconds(String.valueOf(mTotalTime)));
    });
  }
}
