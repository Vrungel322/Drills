package com.udtech.drills.feature.holoshenie_with_timer.presenters;

import android.os.CountDownTimer;
import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.feature.holoshenie_with_timer.views.IHoloshenieWithTimerFragmentView;
import com.udtech.drills.utils.Constants;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import javax.inject.Inject;

/**
 * Created by Vrungel on 12.05.2017.
 */
@InjectViewState public class HoloshenieWithTimerFragmentPresenter
    extends BasePresenter<IHoloshenieWithTimerFragmentView> {

  @Inject RxBus mRxBus;

  private CountDownTimer mCountDownTimerBetweenSets;
  private CountDownTimer mCountDownTimerFirstSignalDelay;
  private int whatTimerRuns = Constants.DELAY_TIMER;
  private Integer mSetsRemain;
  private CountDownTimer mCountDownTimerPractice;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void startTimer(Integer dryPracticsFirstSignalDelay, Integer dryPracticsTimeBetweenSets,
      Integer dryPracticsTime) {

    mCountDownTimerFirstSignalDelay = new CountDownTimer(dryPracticsFirstSignalDelay * 1000, 1) {
      @Override public void onTick(long millisUntilFinished) {
        getViewState().updateCircle(millisUntilFinished, dryPracticsFirstSignalDelay * 1000);
      }

      @Override public void onFinish() {
        getViewState().updateCircle(0, dryPracticsFirstSignalDelay);
        getViewState().nextTimerSettings(Constants.SET_TIMER);
        mCountDownTimerPractice.start();
      }
    }.start();

    mCountDownTimerBetweenSets = new CountDownTimer(dryPracticsTimeBetweenSets *1000 ,1) {
      @Override public void onTick(long millisUntilFinished) {
        getViewState().updateCircle(millisUntilFinished, dryPracticsTimeBetweenSets * 1000);

      }

      @Override public void onFinish() {
        getViewState().updateCircle(0, dryPracticsTimeBetweenSets);
        getViewState().nextTimerSettings(Constants.SET_TIMER);
        mCountDownTimerPractice.start();
      }
    };

    mCountDownTimerPractice = new CountDownTimer(dryPracticsTime * 1000, 1) {
      @Override public void onTick(long millisUntilFinished) {
        getViewState().updateCircle(millisUntilFinished, dryPracticsTime * 1000);
      }

      @Override public void onFinish() {
        getViewState().updateCircle(0, dryPracticsTime);
        getViewState().nextTimerSettings(Constants.DELAY_TIMER);
        mSetsRemain--;
        if (mSetsRemain > 0) {
          mCountDownTimerBetweenSets.start();
        }
        else {
          getViewState().restoreTv();
        }
      }
    };
  }

  public void setsRemain(Integer dryPracticsSetsRemain) {
    mSetsRemain = dryPracticsSetsRemain;
  }
}
