package com.udtech.drills.feature.holoshenie_with_timer.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.feature.holoshenie_with_timer.views.IHoloshenieWithTimerFragmentView;

/**
 * Created by Vrungel on 12.05.2017.
 */
@InjectViewState public class HoloshenieWithTimerFragmentPresenter
    extends BasePresenter<IHoloshenieWithTimerFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
