package com.udtech.drills.feature.start_point.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 25.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IMainActivityView
    extends MvpView {
}
