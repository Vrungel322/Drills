package com.udtech.drills.feature.content.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 28.04.2017.
 */

@InjectViewState public class ContentFragmentPresenter extends BasePresenter<IContentFragmentView> {
  @Inject DataManager mDataManager;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    Subscription subscription = mDataManager.getTotalSetsTime()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().showTotalTime(s));
    addToUnsubscription(subscription);
  }
}
