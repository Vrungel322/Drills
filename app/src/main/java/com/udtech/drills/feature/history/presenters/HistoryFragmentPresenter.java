package com.udtech.drills.feature.history.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.local.mappers.show_history.HistoryForSendToHistoryDayMapper;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.history.views.IHistoryFragmentView;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

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
    Subscription subscription = mDataManager.getHistoryFromDb()
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(historyForSends -> {
          Timber.e(String.valueOf(historyForSends.size()));
          getViewState().setHistoryList(
              new HistoryForSendToHistoryDayMapper().transform(historyForSends));
        });
    addToUnsubscription(subscription);
  }

  public void removeHistoryForSendFromDbByID(List<String> listIdByDay) {
    if (listIdByDay.size() == 0) {
      mDataManager.dropHistoryTable();
      getViewState().removeAllRowsFromHistoryList();
    } else {
      Subscription subscription = Observable.from(listIdByDay)
          .compose(ThreadSchedulers.applySchedulers())
          .concatMap(s -> mDataManager.dellRowFromHistoryTable(s)
              .compose(ThreadSchedulers.applySchedulers()))
          .subscribe(integer -> getViewState().removeFromView());
      addToUnsubscription(subscription);
    }
  }
}
