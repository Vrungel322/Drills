package com.udtech.drills.feature.create_practice.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by vrungel on 03.05.17.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface ICreatePracticeFragmentView
    extends MvpView {
}
