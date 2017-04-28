package com.udtech.drills.feature.login.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by John on 26.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ILoginActivityView
    extends MvpView {
  void playVideo();

  void showBody(String s);

  void allVisible();

  void allGone();

  void showContentActivity();

  void startRegistration();
}
