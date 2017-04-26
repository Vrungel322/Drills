package com.udtech.drills.feature.login.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 26.04.2017.
 */

@InjectViewState public class LoginActivityPresenter extends BasePresenter<ILoginActivityView> {

  @Inject DataManager mDataManager;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    //getViewState().playVideo();
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
}
