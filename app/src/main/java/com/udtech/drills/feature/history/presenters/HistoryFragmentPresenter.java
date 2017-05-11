package com.udtech.drills.feature.history.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.history.views.IHistoryFragmentView;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 11.05.2017.
 */
@InjectViewState public class HistoryFragmentPresenter extends BasePresenter<IHistoryFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;
  @Inject RxBus mRxBus;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    fetchHistoryList();
  }

  private void fetchHistoryList() {
    Subscription subscription = mDataManager.fetchUserData(mUser.getAuthKey())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userDataEntityResponse -> {
          if (userDataEntityResponse.code() == 200) {
            getViewState().setHistoryList(userDataEntityResponse.body().getHistory());
          }
        });
    addToUnsubscription(subscription);
  }
}
