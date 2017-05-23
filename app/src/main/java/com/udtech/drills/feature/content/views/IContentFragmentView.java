package com.udtech.drills.feature.content.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import java.util.List;

/**
 * Created by Vrungel on 28.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IContentFragmentView
    extends MvpView {

  void playVideo();

  void showTotalTime(String s);

  void showMsg(String s);

  void startProgressDialog();

  void stopProgressDialog();

  void fillCalendar(List<Integer> integerList);
}
