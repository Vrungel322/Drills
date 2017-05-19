package com.udtech.drills.feature.content.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.04.2017.
 */

@InjectViewState public class ContentFragmentPresenter extends BasePresenter<IContentFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    updateTotalTime();
    synchronizeData();
  }

  private void updateTotalTime() {
    Subscription subscription = mDataManager.getTotalSetsTime()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(s -> getViewState().showTotalTime(s));
    addToUnsubscription(subscription);
  }

  private void synchronizeData() {
    getViewState().startProgressDialog();
    Subscription subscription = mDataManager.sendUserDataPractic(mUser.getAuthKey())
        .concatMap(booleanResponse -> mDataManager.sendUserDataHistory(mUser.getAuthKey()))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          if (booleanResponse.code() == 200 && booleanResponse.body()) {
            Timber.e("sendDataToServer Done");
            getViewState().stopProgressDialog();
          }
        }, throwable -> {
          getViewState().showMsg("No Internet");
          getViewState().stopProgressDialog();
        });

    addToUnsubscription(subscription);
  }
}