package com.udtech.drills.feature.login.activities;

import android.os.Bundle;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;

/**
 * Created by John on 26.04.2017.
 */

public class LoginActivity extends BaseActivity implements ILoginActivityView {

  @InjectPresenter LoginActivityPresenter mLoginActivityPresenter;

  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);
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
  }
}
