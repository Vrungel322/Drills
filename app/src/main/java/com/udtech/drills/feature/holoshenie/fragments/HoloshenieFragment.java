package com.udtech.drills.feature.holoshenie.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.feature.content.fragments.ContentFragment;
import com.udtech.drills.feature.create_practice.fragments.CreatePracticeFragment;
import com.udtech.drills.feature.holoshenie.adapters.PracticeAdapter;
import com.udtech.drills.feature.holoshenie.presenters.HoloshenieFragmentPresenter;
import com.udtech.drills.feature.holoshenie.views.IHoloshenieFragmentView;
import com.udtech.drills.feature.holoshenie_with_timer.fragments.HoloshenieWithTimerFragment;
import com.udtech.drills.utils.ItemClickSupport;
import java.util.List;
import timber.log.Timber;

public class HoloshenieFragment extends BaseFragment implements IHoloshenieFragmentView {
  @InjectPresenter HoloshenieFragmentPresenter mHoloshenieFragmentPresenter;

  @BindView(R.id.rvPractice) RecyclerView mRecyclerViewPractice;
  @BindView(R.id.tvDone) TextView mTextViewDone;
  @BindView(R.id.ivAdd) ImageView mImageViewAdd;

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
    ItemClickSupport.addTo(mRecyclerViewPractice)
        .setOnItemClickListener((recyclerView, position, v) -> {
          mNavigator.replaceFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
              HoloshenieWithTimerFragment.newInstance(mPracticeAdapter.getItem(position)));
        });
  }

  @Override public void fillInRecyclerView(List<Practic> practic) {
    mPracticeAdapter.addListPractic(practic);
  }

  @OnClick(R.id.tvDone) public void tvDoneClick() {
     mHoloshenieFragmentPresenter.makePost();
    openContentFragment();
  }

  @Override public void openHoloshenieFragment() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieFragment.newInstance());
  }

  @Override public void openContentFragment() {
    mNavigator.removeFragment((AppCompatActivity) getActivity(),this);
  }

  @OnClick(R.id.ivAdd) public void ivAddClick() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
        CreatePracticeFragment.newInstance());
  }
}
