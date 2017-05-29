package com.udtech.drills.feature.holoshenie_with_timer.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.feature.change_practic_settings.fragments.ChangePracticFragment;
import com.udtech.drills.feature.holoshenie_with_timer.presenters.HoloshenieWithTimerFragmentPresenter;
import com.udtech.drills.feature.holoshenie_with_timer.views.IHoloshenieWithTimerFragmentView;
import com.udtech.drills.utils.Constants;
import com.udtech.drills.utils.Converters;
import timber.log.Timber;

/**
 * Created by Vrungel on 12.05.2017.
 */

public class HoloshenieWithTimerFragment extends BaseFragment
    implements IHoloshenieWithTimerFragmentView {
  public static String PRACTIC_KEY = "PRACTIC_KEY";

  @InjectPresenter HoloshenieWithTimerFragmentPresenter mHoloshenieWithTimerFragmentPresenter;

  @BindView(R.id.tvPracticeToChangeName) TextView mTextViewPracticeToChangeName;
  @BindView(R.id.circle_view) DonutProgress mCircleView;
  @BindView(R.id.tvStartStop) TextView mTextViewStartStop;
  @BindView(R.id.tvPracticTime) TextView mTextViewPracticTime;
  @BindView(R.id.tvDelayTime) TextView mTextViewDelayTime;
  @BindView(R.id.tvSetsCount) TextView mTextViewSetsCount;
  @BindView(R.id.bMinusSet) Button mButtonMinusSet;
  @BindView(R.id.bPlusSet) Button mButtonPlusSet;
  @BindView(R.id.view2) View mView;
  @BindView(R.id.tvBack) TextView mTextViewBack;
  @BindView(R.id.tvChange) TextView mTextViewChange;
  @BindView(R.id.tvPracticeDescription) TextView mTextViewPracticeDescription;

  private Practic mPractic;
  private boolean isRunning;
  private Integer mSetsCount;

  public static HoloshenieWithTimerFragment newInstance(Practic item) {
    Bundle args = new Bundle();
    args.putParcelable(PRACTIC_KEY, item);
    HoloshenieWithTimerFragment fragment = new HoloshenieWithTimerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public HoloshenieWithTimerFragment() {
    super(R.layout.fragment_holoshenie_with_timer);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPractic = getArguments().getParcelable(PRACTIC_KEY);
    mSetsCount = mPractic.getDryPracticsSets();
    showToastMessage("" + mSetsCount);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTextViewPracticeToChangeName.setText(mPractic.getDryPracticsName());
    mCircleView.setMax((int) Math.round(
        Converters.stringToDouble(mPractic.getDryPracticsFirstSignalDelay()) * 1000));
    mTextViewPracticTime.setText(
        Converters.milisToSecWithDecimal(Math.round(mPractic.getDryPracticsTime() * 1000)));
    //mTextViewDelayTime.setText(Converters.milisToSecWithDecimal(Long.valueOf(String.valueOf(
    //    Converters.stringToDouble(mPractic.getDryPracticsFirstSignalDelay()) * 1000))));
    //
    mTextViewDelayTime.setText(mPractic.getDryPracticsFirstSignalDelay());

    mTextViewSetsCount.setText(String.valueOf(mSetsCount));
    mTextViewPracticeDescription.setMovementMethod(new ScrollingMovementMethod());
    mTextViewPracticeDescription.setText(mPractic.getDryPracticsDescription());
  }

  @OnClick(R.id.tvBack) public void tvBackClick() {
    mHoloshenieWithTimerFragmentPresenter.updateCurrentPractice(mPractic, mSetsCount);
    mHoloshenieWithTimerFragmentPresenter.addToDbHistoryByPractic(mPractic);
  }

  @OnClick(R.id.bMinusSet) public void bMinusSetClick() {
    if (mSetsCount > 1) {
      mSetsCount--;
      mTextViewSetsCount.setText(String.valueOf(mSetsCount));
    } else {
      showAlertMessage("Error!", "Sets count must be at least 1");
    }
  }

  @OnClick(R.id.bPlusSet) public void bPlusSetClick() {
    mSetsCount++;
    mTextViewSetsCount.setText(String.valueOf(mSetsCount));
  }

  @OnClick(R.id.tvChange) public void tvChangeClick() {
    mNavigator.replaceFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
        ChangePracticFragment.newInstance(mPractic));
  }

  @Override public void updateCircle(long milisUntilFinish, Double dryPracticsFirstSignalDelay) {
    Timber.e(String.valueOf(milisUntilFinish));
    mCircleView.setDonut_progress(
        String.valueOf(dryPracticsFirstSignalDelay.longValue() - milisUntilFinish));
  }

  @Override public void nextTimerSettings(int setTimer) {
    if (setTimer == Constants.DELAY_TIMER) {
      mCircleView.setMax((int) Math.round(
          Converters.stringToDouble(mPractic.getDryPracticsTimeBetweenSets()) * 1000));
      mCircleView.setFinishedStrokeColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    } else {
      mCircleView.setMax(
          Integer.valueOf(String.valueOf(Math.round(mPractic.getDryPracticsTime() * 1000))));
      mCircleView.setFinishedStrokeColor(
          ContextCompat.getColor(getContext(), android.R.color.holo_red_light));
    }
  }

  @Override public void restoreTv() {
    isRunning = !isRunning;
    mTextViewStartStop.setText(getString(R.string.start));
    mTextViewSetsCount.setText(String.valueOf(mSetsCount));
    YoYo.with(Techniques.FadeInDown).duration(1000).playOn(mButtonMinusSet);
    YoYo.with(Techniques.FadeInDown).duration(1000).playOn(mButtonPlusSet);
    YoYo.with(Techniques.FadeInDown).duration(1000).playOn(mView);
    YoYo.with(Techniques.FadeInDown).duration(1000).playOn(mTextViewBack);
    YoYo.with(Techniques.FadeInDown).duration(1000).playOn(mTextViewChange);
  }

  @Override public void updateTextView(int setTimer, long millisUntilFinished) {
    if (setTimer == Constants.SET_TIMER) {
      mTextViewPracticTime.setText(Converters.milisToSecWithDecimal(millisUntilFinished));
    } else {
      mTextViewDelayTime.setText(Converters.milisToSecWithDecimal(millisUntilFinished));
    }
  }

  @Override public void openHoloshenieListFragment() {
    mNavigator.removeFragment((AppCompatActivity) getActivity(), this);
  }

  @Override public void setTvRemainSets(Integer setsRemain) {
    mTextViewSetsCount.setText(String.valueOf(setsRemain));
  }

  @Override public void playReadySound() {
    final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.ready);
    mp.start();
  }

  @Override public void playBeepSound() {
    final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.beep);
    mp.start();
  }

  @OnClick(R.id.tvStartStop) public void tvStartStopClick() {
    if (mTextViewStartStop.getText().toString().equalsIgnoreCase(getString(R.string.start))){
      playReadySound();
    }
    mHoloshenieWithTimerFragmentPresenter.setsRemain(mSetsCount);
    mCircleView.setMax((int) Math.round(
        Converters.stringToDouble(mPractic.getDryPracticsFirstSignalDelay()) * 1000));
    mCircleView.setFinishedStrokeColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    isRunning = !isRunning;
    if (isRunning) {
      mTextViewStartStop.setText(R.string.stop);
      YoYo.with(Techniques.FadeOutUp).delay(500).duration(500).playOn(mButtonMinusSet);
      YoYo.with(Techniques.FadeOutUp).delay(500).duration(500).playOn(mButtonPlusSet);
      YoYo.with(Techniques.FadeOutUp).delay(500).duration(500).playOn(mView);
      YoYo.with(Techniques.FadeOutUp).delay(500).duration(500).playOn(mTextViewBack);
      YoYo.with(Techniques.FadeOutUp).delay(500).duration(500).playOn(mTextViewChange);
      if (isRunning) {
        mHoloshenieWithTimerFragmentPresenter.startTimer(
            Double.valueOf(mPractic.getDryPracticsFirstSignalDelay()),
            Double.valueOf(mPractic.getDryPracticsTimeBetweenSets()),
            Double.valueOf(String.valueOf(mPractic.getDryPracticsTime())));
      }
    } else {
      mHoloshenieWithTimerFragmentPresenter.setsRemain(0);
      mHoloshenieWithTimerFragmentPresenter.stopAllTimers();
      mCircleView.setDonut_progress("0");
      mTextViewPracticTime.setText(
          Converters.milisToSecWithDecimal(Math.round(mPractic.getDryPracticsTime() * 1000)));
      mTextViewStartStop.setText(R.string.start);
    }
  }
}
