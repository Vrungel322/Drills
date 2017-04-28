package com.udtech.drills.feature.registration.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.signUp_Reset.SignUpResetBody;
import com.udtech.drills.feature.registration.views.IRegistrationActivityView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 28.04.2017.
 */
@InjectViewState public class RegistrationActivityPresenter
    extends BasePresenter<IRegistrationActivityView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void startLoginActivity() {
    getViewState().startLoginActivity();
  }

  public void sendRegistrationData(String emailOrPhone) {
    getViewState().showPB();
    Subscription subscription = mDataManager.signUp(new SignUpResetBody(emailOrPhone))
        .compose(ThreadSchedulers.applySchedulers()).subscribe(signUpResetBodyResponse -> {
          if (signUpResetBodyResponse.code() == 200){
            getViewState().hidePB();
            getViewState().showRegistrationDialog();
          }
        });
    addToUnsubscription(subscription);
  }
}
