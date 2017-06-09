package com.udtech.drills.feature.content.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.feature.content.adapters.CalendarAdapter;
import com.udtech.drills.feature.content.adapters.TotalTimeAdapter;
import com.udtech.drills.feature.content.presenters.ContentFragmentPresenter;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.feature.history.fragments.HistoryFragment;
import com.udtech.drills.feature.holoshenie.fragments.HoloshenieFragment;
import com.udtech.drills.feature.login.activities.LoginActivity;
import com.udtech.drills.utils.DialogFactory;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends BaseFragment implements IContentFragmentView {

  @InjectPresenter ContentFragmentPresenter mBookingDetailFragmentPresenter;

  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.ivHoloshenie) ImageView mImageViewHoloshenie;
  @BindView(R.id.ivHistory) ImageView mImageViewHistory;
  @BindView(R.id.tvAllSetsTime) TextView mTextViewAllSetsTime;
  @BindView(R.id.rvCalendar) RecyclerView mRecyclerViewCalendar;
  @BindView(R.id.rvTotalWeekTime) RecyclerView mRecyclerViewTotalWeekTime;

  private ProgressDialog mProgressDialog;
  private CalendarAdapter mCalendarAdapter;
  private TotalTimeAdapter mTotalTimeAdapter;

  public static ContentFragment newInstance() {
    Bundle args = new Bundle();
    ContentFragment fragment = new ContentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ContentFragment() {
    super(R.layout.fragment_content);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mProgressDialog = DialogFactory.createProgressDialog(getContext(), R.string.synchronization);

    //make dialog yellow
    mProgressDialog.getWindow()
        .setBackgroundDrawable(
            new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorAccent)));

    //calendar stuff
    mCalendarAdapter = new CalendarAdapter();
    mRecyclerViewCalendar.setLayoutManager(new GridLayoutManager(getContext(), 7));
    mRecyclerViewCalendar.setAdapter(mCalendarAdapter);
    mTotalTimeAdapter = new TotalTimeAdapter();
    mRecyclerViewTotalWeekTime.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerViewTotalWeekTime.setAdapter(mTotalTimeAdapter);
  }

  @Override public void onStart() {
    super.onStart();
    playVideo();
  }

  @OnClick(R.id.ivHoloshenie) public void ivHoloshenieClicked() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieFragment.newInstance());
  }

  @OnClick(R.id.ivHistory) public void ivHistoryClicked() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
        HistoryFragment.newInstance());
  }

  @OnClick(R.id.tvLogout) public void tvLogoutClicked() {
    mBookingDetailFragmentPresenter.logout();
    mNavigator.startActivity((AppCompatActivity) getActivity(),
        new Intent(getContext(), LoginActivity.class));
  }

  @Override public void playVideo() {
    try {
      mScalableVideoView.setRawData(R.raw.video);
      mScalableVideoView.prepare(mp -> mScalableVideoView.start());
      mScalableVideoView.setVolume(0, 0);
      mScalableVideoView.setLooping(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    mScalableVideoView.setOnErrorListener((mp, what, extra) -> {
      mScalableVideoView.release();
      return false;
    });
  }

  @Override public void showTotalTime(String s) {
    mTextViewAllSetsTime.setText(s);
  }

  @Override public void showMsg(String s) {
    showAlertMessage("Message:", s);
  }

  @Override public void startProgressDialog() {
    mProgressDialog.show();
  }

  @Override public void stopProgressDialog() {
    mProgressDialog.cancel();
  }

  @Override public void fillCalendar(List<Integer> integerList) {
    mCalendarAdapter.addListCalendarItemEntity(integerList);
  }

  @Override public void setTotalTimesPerWeek(List<Double> doubleList) {
    List<Integer> integers = new ArrayList<>();
    for (Double dd : doubleList) {
      integers.add(dd.intValue());
    }
    mTotalTimeAdapter.addListTotalTimes(integers);
  }
}
