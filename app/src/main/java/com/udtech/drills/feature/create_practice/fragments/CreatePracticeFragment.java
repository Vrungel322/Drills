package com.udtech.drills.feature.create_practice.fragments;

import android.os.Bundle;
import android.widget.Toast;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.feature.create_practice.presenters.CreatePracticeFragmentPresenter;
import com.udtech.drills.feature.create_practice.views.ICreatePracticeFragmentView;

/**
 * Created by vrungel on 03.05.17.
 */

public class CreatePracticeFragment extends BaseFragment implements ICreatePracticeFragmentView {
  @InjectPresenter CreatePracticeFragmentPresenter createPracticeFragmentPresenter;

  public static CreatePracticeFragment newInstance() {
    Bundle args = new Bundle();
    CreatePracticeFragment fragment = new CreatePracticeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public CreatePracticeFragment() {
    super(R.layout.fragment_create_practice);
  }

  @OnClick(R.id.tvCreate)
  public void tvCreateClicked(){
    Toast.makeText(getContext(),"tvCreate", Toast.LENGTH_SHORT).show();
    createPracticeFragmentPresenter.sendData();
  }
}
