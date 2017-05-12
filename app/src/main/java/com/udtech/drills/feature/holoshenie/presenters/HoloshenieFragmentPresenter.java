package com.udtech.drills.feature.holoshenie.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.List;
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
  @Inject RxBus mRxBus;
  private List<PracticForSend> mPracticForSend;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUI();
    fetchUserData();
    getInfFromRxBusAboutPracticToSend();
  }

  private void getInfFromRxBusAboutPracticToSend() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SendDataToServer.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(sendDataToServer -> {
          mPracticForSend = sendDataToServer.mPracticForSends;
          sendUserDataPracticToServer();
        });
    addToUnsubscription(subscription);
  }

  public void sendUserDataPracticToServer() {
    Subscription subscription = mDataManager.sendUserDataPractic(mUser.getAuthKey(), mPracticForSend)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          if (booleanResponse.code() == 200 && booleanResponse.body()) {
            Timber.e("sendDataToServer Done");
            getViewState().openHoloshenieFragment();
          }
        });
    addToUnsubscription(subscription);
  }

  private void fetchUserData() {
    Subscription subscription = mDataManager.fetchUserData(mUser.getAuthKey())
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(userDataEntityResponse -> {
          if (userDataEntityResponse.code() == 200) {
            getViewState().fillInRecyclerView(userDataEntityResponse.body().getPractic());
          }
        });
    addToUnsubscription(subscription);
  }
}
