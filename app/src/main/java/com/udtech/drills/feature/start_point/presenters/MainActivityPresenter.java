package com.udtech.drills.feature.start_point.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import com.udtech.drills.feature.start_point.views.IMainActivityView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 25.04.2017.
 */

@InjectViewState public class MainActivityPresenter extends BasePresenter<IMainActivityView> {

  @Inject DataManager mDataManager;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    checkIfUserLoggedIn();
  }

  private void checkIfUserLoggedIn() {
    if (mDataManager.isUserLogin()){
      getViewState().showContentScreen();
    }
    else {
      getViewState().showLoginScreen();
    }
  }

  public void login(String login, String password) {
    Subscription subscription = mDataManager.login(login, password)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userResponse -> {
          if (userResponse.code() == 200) {
            mUser = userResponse.body();
            getViewState().showBody(userResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void signUp(String phoneNumberEmail) {
    Subscription subscription = mDataManager.signUp(new SignUpResetBody(phoneNumberEmail))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(signUpBodyResponse -> {
          if (signUpBodyResponse.code() == 200) {
            getViewState().showBody(signUpBodyResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void resetPass(String phoneNumberEmail) {
    Subscription subscription = mDataManager.resetPass(new SignUpResetBody(phoneNumberEmail))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(signUpBodyResponse -> {
          if (signUpBodyResponse.code() == 200) {
            getViewState().showBody(signUpBodyResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void fetchUserData() {
    Subscription subscription = mDataManager.fetchUserData(mUser.getAuthKey())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userDataEntityResponse -> {
          getViewState().showBody(
              userDataEntityResponse.body().getHistory().get(0).getHistoryPracticsID());
        });
    addToUnsubscription(subscription);
  }

  public void sendUserData() {
    Subscription subscription = mDataManager.sendUserData(mUser.getAuthKey())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          if (booleanResponse.code() == 200) {
            getViewState().showBody(booleanResponse.body().toString());
          }
        });
    addToUnsubscription(subscription);
  }
}