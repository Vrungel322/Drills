package com.udtech.drills.feature.create_practice.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.feature.create_practice.presenters.CreatePracticeFragmentPresenter;
import com.udtech.drills.feature.create_practice.views.ICreatePracticeFragmentView;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.Randomizer;
import com.udtech.drills.utils.ViewUtil;

/**
 * Created by vrungel on 03.05.17.
 */

public class CreatePracticeFragment extends BaseFragment implements ICreatePracticeFragmentView {
  @InjectPresenter CreatePracticeFragmentPresenter createPracticeFragmentPresenter;

  @BindView(R.id.etPracticName) EditText mEditTextPracticName;
  @BindView(R.id.etSetTime) EditText mEditTextSetTime;
  @BindView(R.id.etDelay) EditText mEditTextDelay;
  @BindView(R.id.cbRandDelay) CheckBox mCheckBoxRandDelay;
  @BindView(R.id.etBetweenSets) EditText mEditTextBetweenSets;
  @BindView(R.id.cbRandBetweenSets) CheckBox mCheckBoxRandBetweenSets;
  @BindView(R.id.etComments) EditText mEditTextComments;
  private String delay = "23";
  private String delaySets = "22";

  public static CreatePracticeFragment newInstance() {
    Bundle args = new Bundle();
    CreatePracticeFragment fragment = new CreatePracticeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public CreatePracticeFragment() {
    super(R.layout.fragment_create_practice);
  }

  @OnClick(R.id.tvCreate) public void tvCreateClicked() {
    ViewUtil.hideKeyboard(getActivity());
    createPracticeFragmentPresenter.sendData(mEditTextPracticName.getText().toString(),
        mEditTextSetTime.getText().toString(), delay, delaySets,
        mEditTextComments.getText().toString(),
        Converters.boolToInt(mCheckBoxRandBetweenSets.isChecked()),
        Converters.boolToInt(mCheckBoxRandDelay.isChecked()));
  }

  @OnClick(R.id.tvCancel) public void tvCancelClicked() {
    ViewUtil.hideKeyboard(getActivity());
    mNavigator.removeFragment((AppCompatActivity) getActivity(), this);
  }

  @OnClick(R.id.cbRandDelay) public void cbRandDelayCkicked() {
    if (mCheckBoxRandDelay.isChecked()) {

      if (mEditTextDelay.getText().toString().contains("3..")) {
        delay = String.valueOf(Randomizer.getRandomNumberInRange(3, Integer.parseInt("22")));
      } else {
        delay = String.valueOf(Randomizer.getRandomNumberInRange(3,
            Integer.parseInt(mEditTextDelay.getText().toString())));
      }
      mEditTextDelay.setText("3.." + delay);
    }
  }

  @OnClick(R.id.cbRandBetweenSets) public void cbRandBetweenSetsCkicked() {
    if (mCheckBoxRandBetweenSets.isChecked()) {

      if (mEditTextBetweenSets.getText().toString().contains("3..")) {
        delaySets = String.valueOf(Randomizer.getRandomNumberInRange(3, Integer.parseInt("23")));
      } else {
        delaySets = String.valueOf(Randomizer.getRandomNumberInRange(3,
            Integer.parseInt(mEditTextBetweenSets.getText().toString())));
      }
      mEditTextBetweenSets.setText("3.." + delaySets);
    }
  }
}
