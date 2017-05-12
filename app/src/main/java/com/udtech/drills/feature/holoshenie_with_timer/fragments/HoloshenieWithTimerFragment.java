package com.udtech.drills.feature.holoshenie_with_timer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.feature.holoshenie.fragments.HoloshenieFragment;
import com.udtech.drills.feature.holoshenie_with_timer.presenters.HoloshenieWithTimerFragmentPresenter;
import com.udtech.drills.feature.holoshenie_with_timer.views.IHoloshenieWithTimerFragmentView;
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

  private Practic mPractic;

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
    Timber.e(mPractic.getDryPracticsName());
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mTextViewPracticeToChangeName.setText(mPractic.getDryPracticsName());
    mCircleView.setMax(mPractic.getDryPracticsFirstSignalDelay()*1000);
    mCircleView.setDonut_progress("70");
    mHoloshenieWithTimerFragmentPresenter.startTimer(mPractic.getDryPracticsFirstSignalDelay());
  }

  @OnClick(R.id.tvBack) public void tvBackClick() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieFragment.newInstance());
  }

  @OnClick(R.id.tvChange) public void tvChangeClick() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieFragment.newInstance());
  }

  @Override public void updateCircle(long milisUntilFinish, Integer dryPracticsFirstSignalDelay) {
    Timber.e(String.valueOf(milisUntilFinish));
    mCircleView.setDonut_progress(String.valueOf(dryPracticsFirstSignalDelay - milisUntilFinish));
  }
}
