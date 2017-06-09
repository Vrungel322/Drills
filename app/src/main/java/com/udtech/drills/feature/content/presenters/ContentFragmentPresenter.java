package com.udtech.drills.feature.content.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.local.mappers.show_history.GroupingDaysIntoWeeks;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 28.04.2017.
 */

@InjectViewState public class ContentFragmentPresenter extends BasePresenter<IContentFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;
  @Inject RxBus mRxBus;
  private Double mTotalTime;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    getRxBuxInfoToSyncData();
    synchronizeData();
  }

  private void getRxBuxInfoToSyncData() {
    Subscription subscription = mRxBus.filteredObservable(RxBusHelper.SynchronizeData.class)
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(synchronizeData -> {
          synchronizeData();
          getTotalTimingByWeeks();
          return Observable.just("");
        })
        .subscribe(o -> {
        });
    addToUnsubscription(subscription);
  }

  private void synchronizeData() {
    getViewState().startProgressDialog();
    Subscription subscription = mDataManager.sendUserDataPractic(mUser.getAuthKey())
        .concatMap(booleanResponse -> mDataManager.sendUserDataHistory(mUser.getAuthKey()))
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          if (booleanResponse.code() == 200 && booleanResponse.body()) {
            updateTotalTime();
            getDaysForCalendar();
            getTotalTimingByWeeks();
            Timber.e("sendDataToServer Done");
            getViewState().stopProgressDialog();
          }
        }, throwable -> {
          getViewState().showMsg("No Internet");
          getViewState().stopProgressDialog();
        });

    addToUnsubscription(subscription);
  }

  private void getTotalTimingByWeeks() {
    Subscription subscription =
        mDataManager.getHistoryFromDb().compose(ThreadSchedulers.applySchedulers()).
            concatMap(historyForSendList -> {
              //4 weeks time total
              List<Double> listTotalTimeOfTheWeek =
                  new GroupingDaysIntoWeeks().getListTotalTimeOfTheWeek(historyForSendList);
              return Observable.just(listTotalTimeOfTheWeek);
            }).subscribe(integerList -> {
          getViewState().setTotalTimesPerWeek(integerList);
        });
    addToUnsubscription(subscription);
  }

  private void getDaysForCalendar() {
    Subscription subscription =
        mDataManager.getHistoryFromDb().compose(ThreadSchedulers.applySchedulers()).
            concatMap(historyForSendList -> {

              //28 day
              List<Integer> listStatusOfDay =
                  new GroupingDaysIntoWeeks().getListStatusOfDay(historyForSendList);
              return Observable.just(listStatusOfDay);
            }).subscribe(integerList -> {
          getViewState().fillCalendar(integerList);
        });
    addToUnsubscription(subscription);
  }

  private void updateTotalTime() {
    mTotalTime = Double.valueOf("0");
    Subscription subscription = mDataManager.getHistoryFromDb()
        .compose(ThreadSchedulers.applySchedulers())
        .concatMap(historyForSendList -> {
          //4 weeks time total
          List<Double> listTotalTimeOfTheWeek =
              new GroupingDaysIntoWeeks().getListTotalTimeOfTheWeek(historyForSendList);
          return Observable.from(listTotalTimeOfTheWeek);
        })
        .concatMap(integer -> Observable.just(mTotalTime += integer))
        .subscribe(mTotalTime -> {
          getViewState().showTotalTime(Converters.timeFromSeconds(String.valueOf(mTotalTime.longValue())));
        });
    addToUnsubscription(subscription);
  }

  public void logout() {
    mDataManager.logout();
  }
}