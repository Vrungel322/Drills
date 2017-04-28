package com.udtech.drills.feature.holoshenie.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import java.util.List;

/**
 * Created by Vrungel on 28.04.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class) public interface IHoloshenieFragmentView
    extends MvpView {
  void setUpUI();

  void fillInRecyclerView(List<Practic> practic);
}
