package com.udtech.drills.feature.content.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.feature.content.views.IContentActivityView;
import javax.inject.Inject;

/**
 * Created by Vrungel on 28.04.2017.
 */
@InjectViewState public class ContentActivityPresenter extends BasePresenter<IContentActivityView> {

  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().showContentFragment();
  }
}
