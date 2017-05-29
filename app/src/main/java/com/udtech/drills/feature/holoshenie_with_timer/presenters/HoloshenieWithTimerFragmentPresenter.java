package com.udtech.drills.feature.holoshenie_with_timer.presenters;

import android.os.CountDownTimer;
import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.local.mappers.PracticToPracticForSendMapper;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.holoshenie_with_timer.views.IHoloshenieWithTimerFragmentView;
import com.udtech.drills.utils.Constants;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.Randomizer;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Vrungel on 12.05.2017.
 */
@InjectViewState public class HoloshenieWithTimerFragmentPresenter
    extends BasePresenter<IHoloshenieWithTimerFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;
  @Inject RxBus mRxBus;

  private CountDownTimer mCountDownTimerBetweenSets;
  private CountDownTimer mCountDownTimerFirstSignalDelay;
  private int whatTimerRuns = Constants.DELAY_TIMER;
  private Integer mSetsRemain;
  private CountDownTimer mCountDownTimerPractice;
  private CountDownTimer mCountDownTimerReadySound;
  private Integer mDoneSetsCount = 0;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void startTimer(long dryPracticsFirstSignalDelay, long dryPracticsTimeBetweenSets,
      long dryPracticsTime) {

    mCountDownTimerReadySound = new CountDownTimer(2000, 1) {
      @Override public void onTick(long millisUntilFinished) {

      }

      @Override public void onFinish() {
        mCountDownTimerFirstSignalDelay.start();
        getViewState().playBeepSound();
      }
    }.start();

    mCountDownTimerFirstSignalDelay =
        new CountDownTimer(dryPracticsFirstSignalDelay, 1) {
          @Override public void onTick(long millisUntilFinished) {
            getViewState().updateCircle(millisUntilFinished, dryPracticsFirstSignalDelay * 1000);
            getViewState().updateTextView(Constants.DELAY_TIMER, millisUntilFinished);
          }

          @Override public void onFinish() {
            getViewState().updateCircle(0, dryPracticsFirstSignalDelay);
            getViewState().nextTimerSettings(Constants.SET_TIMER);
            getViewState().updateTextView(Constants.DELAY_TIMER, 0);
            mCountDownTimerPractice.start();
          }
        };

    mCountDownTimerBetweenSets =
        new CountDownTimer(dryPracticsTimeBetweenSets, 1) {
          @Override public void onTick(long millisUntilFinished) {
            getViewState().updateCircle(millisUntilFinished, dryPracticsTimeBetweenSets * 1000);
            getViewState().updateTextView(Constants.DELAY_TIMER, millisUntilFinished);
          }

          @Override public void onFinish() {
            getViewState().updateCircle(0, dryPracticsTimeBetweenSets);
            getViewState().nextTimerSettings(Constants.SET_TIMER);
            getViewState().updateTextView(Constants.DELAY_TIMER, 0);
            mCountDownTimerPractice.start();
          }
        };

    mCountDownTimerPractice = new CountDownTimer(dryPracticsTime, 1) {
      @Override public void onTick(long millisUntilFinished) {
        getViewState().updateCircle(millisUntilFinished, dryPracticsTime * 1000);
        getViewState().updateTextView(Constants.SET_TIMER, millisUntilFinished);
      }

      @Override public void onFinish() {
        getViewState().updateCircle(0, dryPracticsTime);
        getViewState().nextTimerSettings(Constants.DELAY_TIMER);
        getViewState().updateTextView(Constants.SET_TIMER, 0);
        mSetsRemain--;
        if (mSetsRemain > 0) {
          mCountDownTimerBetweenSets.start();
          getViewState().setTvRemainSets(mSetsRemain);
        } else {
          getViewState().restoreTv();
        }
        mDoneSetsCount++;
      }
    };
  }

  public void setsRemain(Integer dryPracticsSetsRemain) {
    mSetsRemain = dryPracticsSetsRemain;
  }

  public void stopAllTimers() {
    if (mCountDownTimerFirstSignalDelay != null) {
      mCountDownTimerFirstSignalDelay.cancel();
    }
    if (mCountDownTimerBetweenSets != null) {
      mCountDownTimerBetweenSets.cancel();
    }
    if (mCountDownTimerPractice != null) {
      mCountDownTimerPractice.cancel();
    }
  }

  public void updateCurrentPractice(Practic practic, Integer setsCount) {
    ArrayList<PracticForSend> practicForSends = new ArrayList<>();
    practic.setDryPracticsSets(setsCount);
    practicForSends.add(new PracticToPracticForSendMapper().transform(practic));
    Subscription subscription = mDataManager.putPracticToDb(practicForSends)
        .compose(ThreadSchedulers.applySchedulers())
        .subscribe(booleanResponse -> {
          getViewState().openHoloshenieListFragment();
        });
    addToUnsubscription(subscription);
  }

  public void addToDbHistoryByPractic(Practic practic) {
    HistoryForSend historyForSend =
        new HistoryForSend(practic.getDryPracticsSets(), practic.getDryPracticsName(),
            Converters.doubleToInteger(practic.getDryPracticsTime()), Randomizer.randomString(30),
            Constants.OBJECT_TYPE,
            Converters.stringToDouble(String.valueOf(System.currentTimeMillis())));
    historyForSend.setHistoryPracticsSets(mDoneSetsCount);
    if (historyForSend.getHistoryPracticsSets() != 0){
      mDataManager.addHistoryToDb(historyForSend);
    }
  }
}
