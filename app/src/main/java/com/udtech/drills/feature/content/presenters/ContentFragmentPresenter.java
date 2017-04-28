package com.udtech.drills.feature.content.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.feature.content.views.IContentFragmentView;

/**
 * Created by Vrungel on 28.04.2017.
 */

@InjectViewState public class ContentFragmentPresenter extends BasePresenter<IContentFragmentView> {
  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }
}
