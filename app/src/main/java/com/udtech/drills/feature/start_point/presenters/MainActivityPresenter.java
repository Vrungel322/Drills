package com.udtech.drills.feature.start_point.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.feature.start_point.views.IMainActivityView;

/**
 * Created by Vrungel on 25.04.2017.
 */

@InjectViewState public class MainActivityPresenter extends BasePresenter<IMainActivityView> {

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
  }

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}