package com.udtech.drills.feature.change_practic_settings.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEditTextPracticName.setText(mPractic.getDryPracticsName());
    mEditTextSetTime.setText(String.valueOf(mPractic.getDryPracticsTime()));
    mEditTextDelay.setText(String.valueOf(mPractic.getDryPracticsFirstSignalDelay()));
    mCheckBoxRandDelay.setChecked(
        Converters.intToBool(mPractic.getBoolIsRandPracticsFirstSignalDelay()));
    mEditTextBetweenSets.setText(String.valueOf(mPractic.getDryPracticsTimeBetweenSets()));
    mCheckBoxRandBetweenSets.setChecked(
        Converters.intToBool(mPractic.getBoolIsRandPracticsTimeBetweenSets()));
    mEditTextComments.setText(mPractic.getDryPracticsDescription());
  }

  @OnClick(R.id.tvSave) public void tvSaveClicked() {
    if (Integer.parseInt(mEditTextDelay.getText().toString()) > 2
        || Integer.parseInt(mEditTextBetweenSets.getText().toString()) > 2) {
      
      mPractic.setDryPracticsName(mEditTextPracticName.getText().toString());
      mPractic.setDryPracticsTime(Double.valueOf(mEditTextSetTime.getText().toString()));
      mPractic.setDryPracticsFirstSignalDelay(mEditTextDelay.getText().toString());
      mPractic.setBoolIsRandPracticsFirstSignalDelay(
          Converters.boolToInt(mCheckBoxRandDelay.isChecked()));
      mPractic.setDryPracticsTimeBetweenSets(mEditTextBetweenSets.getText().toString());
      mPractic.setBoolIsRandPracticsTimeBetweenSets(
          Converters.boolToInt(mCheckBoxRandBetweenSets.isChecked()));
      mPractic.setDryPracticsDescription(mEditTextComments.getText().toString());
      mChangePracticFragmentPresenter.updateCurrentPractice(mPractic);

      mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
          HoloshenieWithTimerFragment.newInstance(mPractic));
    } else {
      showAlertMessage(getString(R.string.dialog_error_title),
          getString(R.string.wrong_delay_data));
    }
  }

  @OnClick(R.id.tvCancel) public void tvCancelClicked() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieWithTimerFragment.newInstance(mPractic));
  }

  @OnClick(R.id.cbRandDelay) public void cbRandDelayCkicked() {
    if (mCheckBoxRandDelay.isChecked()) {
      if (mEditTextDelay.getText().toString().isEmpty()) {
        showToastMessage("Invalid input");
      } else {
        mEditTextDelay.setText(String.valueOf(Randomizer.getRandomNumberInRange(3,
            Integer.parseInt(mEditTextDelay.getText().toString()))));
      }
    }
  }

  @OnClick(R.id.cbRandBetweenSets) public void cbRandBetweenSetsCkicked() {
    if (mCheckBoxRandBetweenSets.isChecked()) {
      if (mEditTextBetweenSets.getText().toString().isEmpty()) {
        showToastMessage("Invalid input");
      } else {
        mEditTextBetweenSets.setText(String.valueOf(Randomizer.getRandomNumberInRange(3,
            Integer.parseInt(mEditTextBetweenSets.getText().toString()))));
      }
    }
  }

  @Override public void openHoloshenieWithtimerAfterChange() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieWithTimerFragment.newInstance(mPractic));
  }
}
