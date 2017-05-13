package com.udtech.drills.feature.change_practic_settings.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 13.05.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IChangePracticFragmentView
    extends MvpView {
  void openHoloshenieWithtimerAfterChange();
}
