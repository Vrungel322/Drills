package com.udtech.drills.feature.holoshenie.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.feature.holoshenie.adapters.PracticeAdapter;
import com.udtech.drills.feature.holoshenie.presenters.HoloshenieFragmentPresenter;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;
import java.util.List;
import timber.log.Timber;

public class HoloshenieFragment extends BaseFragment implements IHoloshenieFragmentView {
  @InjectPresenter HoloshenieFragmentPresenter mHoloshenieFragmentPresenter;

  @BindView(R.id.rvPractice) RecyclerView mRecyclerViewPractice;

  private PracticeAdapter mPracticeAdapter;

  public static HoloshenieFragment newInstance() {
    Bundle args = new Bundle();
    HoloshenieFragment fragment = new HoloshenieFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public HoloshenieFragment() {
    super(R.layout.fragment_holoshenie);
  }

  @Override public void setUpUI() {
    mPracticeAdapter = new PracticeAdapter();
    mRecyclerViewPractice.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewPractice.setAdapter(mPracticeAdapter);

  }

  @Override public void fillInRecyclerView(List<Practic> practic) {

    Timber.e("" + practic.size());
    mPracticeAdapter.addListPractic(practic);
  }
}
