package com.udtech.drills.feature.registration.views;

/**
 * Created by Vrungel on 28.04.2017.
 */

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class) public interface IRegistrationActivityView
    extends MvpView {
  void playVideo();

  void startLoginActivity();

  void showRegistrationDialog();
}
