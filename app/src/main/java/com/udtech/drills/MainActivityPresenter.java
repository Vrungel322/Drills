package com.udtech.drills;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.base.BasePresenter;

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