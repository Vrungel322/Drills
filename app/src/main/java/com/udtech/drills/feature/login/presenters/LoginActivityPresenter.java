package com.udtech.drills.feature.login.presenters;

import android.os.CountDownTimer;
import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.fetch_user_data.UserDataEntity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by John on 26.04.2017.
 */

@InjectViewState public class LoginActivityPresenter extends BasePresenter<ILoginActivityView> {

  @Inject DataManager mDataManager;
  @Inject User mUser;
  @Inject UserDataEntity mUserDataEntity;
  private CountDownTimer mCountDownTimer;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  public void startCountingTimer(int delay) {
    mCountDownTimer = new CountDownTimer(delay, 1000) {
      @Override public void onTick(long millisUntilFinished) {

      }

      @Override public void onFinish() {
        getViewState().allGone();
      }
    }.start();
  }

  public void cancelTimer() {
    mCountDownTimer.cancel();
  }

  public void allVisible() {
    getViewState().allVisible();
  }

  public void login(String login, String password) {
    getViewState().showPB();
    Subscription subscription = mDataManager.login(login, password).concatMap(userResponse -> {
      fillUserEntity(userResponse.body());
      return Observable.just(userResponse);
    }).concatMap(userResponse -> {
      Subscription subscription1 = mDataManager.fetchUserData(userResponse.body().getAuthKey())
          .compose(ThreadSchedulers.applySchedulers())
          .subscribe(userDataEntityResponse -> {
            fillUserDataEntityResponse(userDataEntityResponse.body());
          });
      addToUnsubscription(subscription1);
      return Observable.just(userResponse);
    }).compose(ThreadSchedulers.applySchedulers()).subscribe(userResponse -> {
      if (userResponse.code() == 200) {
        getViewState().showContentActivity();
        mDataManager.userLoggedIn();
        getViewState().hidePB();
      }
    }, Timber::e);
    addToUnsubscription(subscription);
  }

  private void fillUserDataEntityResponse(UserDataEntity body) {
    mUserDataEntity.setHistory(body.getHistory());
    mUserDataEntity.setPractic(body.getPractic());
  }

  private void fillUserEntity(User body) {
    mUser.setId(body.getId());
    mUser.setUsername(body.getUsername());
    mUser.setAuthKey(body.getAuthKey());
    mUser.setName(body.getName());
    mUser.setLastname(body.getLastname());
    mUser.setProfilePhoto(body.getProfilePhoto());
    mUser.setTypeId(body.getTypeId());
    mUser.setType(body.getType());
    mUser.setTeam(body.getTeam());
    mUser.setPlayer(body.getPlayer());
    mUser.setEmail(body.getEmail());
    mUser.setPhone(body.getPhone());
    mUser.setCountry(body.getCountry());
    mUser.setRegion(body.getRegion());
    mUser.setCity(body.getCity());
  }

  public void resetPassword(String resetString) {
    Subscription subscription = mDataManager.resetPass(new SignUpResetBody(resetString))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userResponse -> {
          if (userResponse.code() == 200) {
            getViewState().showBody(userResponse.body().toString());
          }
        }, Timber::e);
    addToUnsubscription(subscription);
  }

  public void startRegistration() {
    getViewState().startRegistration();
  }
}
