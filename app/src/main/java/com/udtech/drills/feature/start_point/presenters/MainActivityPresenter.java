package com.udtech.drills.feature.start_point.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.signUp.SignUpBody;
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

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void login(String login, String password) {
    Subscription subscription = mDataManager.login(login, password)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userResponse -> {
          if (userResponse.code() == 200) {
            getViewState().showBody(userResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void signUp(String phoneNumber) {
    Subscription subscription = mDataManager.signUp(new SignUpBody(phoneNumber))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(signUpBodyResponse -> {
          if (signUpBodyResponse.code() == 200){
            getViewState().showBody(signUpBodyResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }
}