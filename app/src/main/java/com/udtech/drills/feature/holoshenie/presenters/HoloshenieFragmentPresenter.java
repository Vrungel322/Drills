package com.udtech.drills.feature.holoshenie.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;
import com.udtech.drills.utils.ThreadSchedulers;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.04.2017.
 */
@InjectViewState public class HoloshenieFragmentPresenter
    extends BasePresenter<IHoloshenieFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUI();
    fetchUserData();
    Timber.e(mUser.getAuthKey());
  }

  private void fetchUserData() {
    Subscription subscription = mDataManager.fetchUserData(mUser.getAuthKey())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userDataEntityResponse -> {
          if (userDataEntityResponse.code() == 200){
            getViewState().fillInRecyclerView(userDataEntityResponse.body().getPractic());
          }
        });
    addToUnsubscription(subscription);
  }
}
