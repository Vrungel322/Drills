package com.udtech.drills.feature.content.fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.feature.content.presenters.ContentFragmentPresenter;
import com.udtech.drills.feature.content.views.IContentFragmentView;
import com.udtech.drills.feature.holoshenie.fragments.HoloshenieFragment;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;
import timber.log.Timber;

public class ContentFragment extends BaseFragment implements IContentFragmentView {

  @InjectPresenter ContentFragmentPresenter mBookingDetailFragmentPresenter;

  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.ivHoloshenie) ImageView mImageViewHoloshenie;
  @BindView(R.id.ivHistory) ImageView mImageViewHistory;

  public static ContentFragment newInstance() {
    Bundle args = new Bundle();
    ContentFragment fragment = new ContentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ContentFragment() {
    super(R.layout.fragment_content);
  }

  @Override public void onStart() {
    super.onStart();
    playVideo();
  }

  @OnClick(R.id.ivHoloshenie) public void ivHoloshenieClicked() {
    mNavigator.addFragmentBackStack((AppCompatActivity) getActivity(), R.id.contentContainer,
        HoloshenieFragment.newInstance());
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
}
