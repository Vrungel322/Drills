package com.udtech.drills.feature.content.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.feature.content.presenters.ContentFragmentPresenter;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.feature.history.fragments.HistoryFragment;
import com.udtech.drills.feature.holoshenie.fragments.HoloshenieFragment;
import com.udtech.drills.feature.login.activities.LoginActivity;
import com.udtech.drills.utils.DialogFactory;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;

public class ContentFragment extends BaseFragment implements IContentFragmentView {

  @InjectPresenter ContentFragmentPresenter mBookingDetailFragmentPresenter;

  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.ivHoloshenie) ImageView mImageViewHoloshenie;
  @BindView(R.id.ivHistory) ImageView mImageViewHistory;
  @BindView(R.id.tvAllSetsTime) TextView mTextViewAllSetsTime;

  private ProgressDialog mProgressDialog;

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
    mProgressDialog.getWindow()
        .setBackgroundDrawable(
            new ColorDrawable(ContextCompat.getColor(getContext(), R.color.colorAccent)));
  }

  @Override public void onStart() {
    super.onStart();
    playVideo();
  }

  @OnClick(R.id.ivHoloshenie) public void ivHoloshenieClicked() {
    mNavigator.replaceFragment((AppCompatActivity) getActivity(), R.id.contentContainer,
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
}
