package com.udtech.drills.feature.holoshenie.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;

/**
 * Created by Vrungel on 28.04.2017.
 */
@InjectViewState
public class HoloshenieFragmentPresenter extends BasePresenter<IHoloshenieFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
