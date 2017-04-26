package com.udtech.drills.feature.login.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;
import timber.log.Timber;

/**
 * Created by John on 26.04.2017.
 */

public class LoginActivity extends BaseActivity implements ILoginActivityView {

  @InjectPresenter LoginActivityPresenter mLoginActivityPresenter;

  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.etPhoneEmail) EditText mEditTextPhoneEmail;
  @BindView(R.id.etPassword) EditText mEditTextPassword;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);
  }

  @Override protected void onStart() {
    super.onStart();
    playVideo();
  }

  @OnClick(R.id.bLogin) public void bLoginClicked() {
    mLoginActivityPresenter.login(mEditTextPhoneEmail.getText().toString(),
        mEditTextPassword.getText().toString());
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

  @Override public void showBody(String s) {
    showToastMessage(s);
  }
}
