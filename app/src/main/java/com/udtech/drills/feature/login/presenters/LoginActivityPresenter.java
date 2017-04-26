package com.udtech.drills.feature.login.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import javax.inject.Inject;

/**
 * Created by John on 26.04.2017.
 */

@InjectViewState public class LoginActivityPresenter extends BasePresenter<ILoginActivityView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().playVideo();
  }
}
