package com.udtech.drills.feature.history.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.udtech.drills.data.local.mappers.show_history.HistoryDay;
import java.util.List;

/**
 * Created by Vrungel on 11.05.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IHistoryFragmentView
    extends MvpView {
  void setHistoryList(List<HistoryDay> history);

  void removeFromView();
}
