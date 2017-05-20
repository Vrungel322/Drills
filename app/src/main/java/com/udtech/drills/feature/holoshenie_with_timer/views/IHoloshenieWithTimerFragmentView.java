package com.udtech.drills.feature.holoshenie_with_timer.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 12.05.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IHoloshenieWithTimerFragmentView
    extends MvpView {
  void updateCircle(long milisUntilFinish,Integer dryPracticsFirstSignalDelay);

  void nextTimerSettings(int setTimer);

  void restoreTv();

  void updateTextView(int setTimer, long millisUntilFinished);

  void openHoloshenieListFragment();

  void setTvRemainSets(Integer setsRemain);
}
