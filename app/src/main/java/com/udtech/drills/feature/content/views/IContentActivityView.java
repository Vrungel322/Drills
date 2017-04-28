package com.udtech.drills.feature.content.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Vrungel on 28.04.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class) public interface IContentActivityView
    extends MvpView {
}
