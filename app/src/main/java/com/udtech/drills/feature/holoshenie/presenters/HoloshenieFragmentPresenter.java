package com.udtech.drills.feature.holoshenie.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.04.2017.
 */
@InjectViewState public class HoloshenieFragmentPresenter
    extends BasePresenter<IHoloshenieFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;
  //@Inject UserDataEntity mUserDataEntity;
  @Inject RxBus mRxBus;
  private List<PracticForSend> mPracticForSend = new ArrayList<>();

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getViewState().setUpUI();
    fetchUserDataFromDb();

    getInfFromRxBusAboutPracticForSendingToDb();
  }

  private void fetchUserDataFromDb() {
    Subscription subscription = mDataManager.getPracticsFromDb()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(practics -> {
          getViewState().fillInRecyclerView(practics);
        });
    addToUnsubscription(subscription);
  }

  private void getInfFromRxBusAboutPracticForSendingToDb() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SendDataToDb.class)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(sendDataToDb -> {
          mPracticForSend = sendDataToDb.mPracticForSends;
          sendUserDataPracticToDB();
        });
    addToUnsubscription(subscription);
  }

  private void sendUserDataPracticToDB() {
    Subscription subscription = mDataManager.putPracticToDb(mPracticForSend)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          Timber.e("sendUserDataPracticToDB Done");
          getViewState().openHoloshenieFragment();
        });
    addToUnsubscription(subscription);
  }

  public void makePost() {
    mRxBus.post(new RxBusHelper.SynchronizeData());
  }

  public void dellPractics(List<Practic> listPracticsToRemove) {
    if (listPracticsToRemove.size() == 0) {
      mDataManager.dropPracticsTable();
      getViewState().removeAllRowsFromPracticsList();
    } else {
      Timber.e(String.valueOf(listPracticsToRemove.size()));

      Subscription subscription = Observable.from(listPracticsToRemove)
          .compose(ThreadSchedulers.applySchedulers())
          .concatMap(practic -> mDataManager.dellRowFromPracticsTable(practic)
              .compose(ThreadSchedulers.applySchedulers()))
          .subscribe(integer -> getViewState().removeFromView());
      addToUnsubscription(subscription);
    }
  }

  //public void sendUserDataPracticToServer() {
  //  Subscription subscription = mDataManager.sendUserDataPractic(mUser.getAuthKey())
  //      .concatMap(booleanResponse -> mDataManager.sendUserDataHistory(mUser.getAuthKey()))
  //      .compose(ThreadSchedulers.applySchedulers())
  //      .subscribe(booleanResponse -> {
  //        if (booleanResponse.code() == 200 && booleanResponse.body()) {
  //          Timber.e("sendDataToServer Done");
  //          getViewState().openContentFragment();
  //        }
  //      });
  //  addToUnsubscription(subscription);
  //}

  //private void fetchUserData() {
  //  Subscription subscription = mDataManager.fetchUserData(mUser.getAuthKey())
  //      .compose(ThreadSchedulers.applySchedulers())
  //      .subscribe(userDataEntityResponse -> {
  //        if (userDataEntityResponse.code() == 200) {
  //          mUserDataEntity = userDataEntityResponse.body();
  //          getViewState().fillInRecyclerView(userDataEntityResponse.body().getPractic());
  //        }
  //      });
  //  addToUnsubscription(subscription);
  //}
}
