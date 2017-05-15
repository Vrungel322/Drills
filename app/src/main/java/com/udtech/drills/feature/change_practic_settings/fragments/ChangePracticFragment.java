package com.udtech.drills.feature.change_practic_settings.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.feature.change_practic_settings.presenters.ChangePracticFragmentPresenter;
import com.udtech.drills.feature.change_practic_settings.views.IChangePracticFragmentView;
import com.udtech.drills.feature.holoshenie_with_timer.fragments.HoloshenieWithTimerFragment;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.Randomizer;

/**
 * Created by Vrungel on 13.05.2017.
 */

public class ChangePracticFragment extends BaseFragment implements IChangePracticFragmentView {
  public static String PRACTIC_KEY_to_change = "PRACTIC_KEY_to_change";
  @InjectPresenter ChangePracticFragmentPresenter mChangePracticFragmentPresenter;

  @BindView(R.id.etPracticName) EditText mEditTextPracticName;
  @BindView(R.id.etSetTime) EditText mEditTextSetTime;
  @BindView(R.id.etDelay) EditText mEditTextDelay;
  @BindView(R.id.cbRandDelay) CheckBox mCheckBoxRandDelay;
  @BindView(R.id.etBetweenSets) EditText mEditTextBetweenSets;
  @BindView(R.id.cbRandBetweenSets) CheckBox mCheckBoxRandBetweenSets;
  @BindView(R.id.etComments) EditText mEditTextComments;

  private Practic mPractic;

  public static ChangePracticFragment newInstance(Practic practic) {
    Bundle args = new Bundle();
    args.putParcelable(PRACTIC_KEY_to_change, practic);
    ChangePracticFragment fragment = new ChangePracticFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ChangePracticFragment() {
    super(R.layout.fragment_change_practic);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPractic = getArguments().getParcelable(PRACTIC_KEY_to_change);
  }

  @OnClick(R.id.tvSave) public void tvSaveClicked() {
    mPractic.setDryPracticsName(mEditTextPracticName.getText().toString());
    mPractic.setDryPracticsTime(Double.valueOf(mEditTextSetTime.getText().toString()));
    mPractic.setDryPracticsFirstSignalDelay(Integer.valueOf(mEditTextDelay.getText().toString()));
    mPractic.setBoolIsRandPracticsFirstSignalDelay(
        Converters.boolToInt(mCheckBoxRandDelay.isChecked()));
    mPractic.setDryPracticsTimeBetweenSets(
        Integer.valueOf(mEditTextBetweenSets.getText().toString()));
    mPractic.setBoolIsRandPracticsTimeBetweenSets(
        Converters.boolToInt(mCheckBoxRandBetweenSets.isChecked()));
    // TODO: 15.05.2017 update in bd 
    //mChangePracticFragmentPresenter.updateCurrentPractice(mPractic);

    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieWithTimerFragment.newInstance(mPractic));
  }

  @OnClick(R.id.tvCancel) public void tvCancelClicked() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieWithTimerFragment.newInstance(mPractic));
  }

  @OnClick(R.id.cbRandDelay) public void cbRandDelayCkicked() {
    mEditTextDelay.setText(String.valueOf(Randomizer.getRandomNumberInRange(3, 70)));
  }

  @OnClick(R.id.cbRandBetweenSets) public void cbRandBetweenSetsCkicked() {
    mEditTextBetweenSets.setText(String.valueOf(Randomizer.getRandomNumberInRange(3, 70)));
  }

  @Override public void openHoloshenieWithtimerAfterChange() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieWithTimerFragment.newInstance(mPractic));
  }
}
