package com.udtech.drills.feature.registration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.login.activities.LoginActivity;
import com.udtech.drills.feature.registration.presenters.RegistrationActivityPresenter;
import com.udtech.drills.feature.registration.views.IRegistrationActivityView;
import com.udtech.drills.utils.DialogFactory;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;

public class RegistrationActivity extends BaseActivity implements IRegistrationActivityView {
  @InjectPresenter RegistrationActivityPresenter mRegistrationActivityPresenter;

  @BindView(R.id.clParent) ConstraintLayout mConstraintLayoutParent;
  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.etPhoneEmail) EditText mEditTextPhoneEmail;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_registration);
    super.onCreate(savedInstanceState);
  }

  @Override protected void onStart() {
    super.onStart();
    playVideo();
  }

  @OnClick(R.id.tvCancel) public void tvCancelClicked() {
    mRegistrationActivityPresenter.startLoginActivity();
  }

  @OnClick(R.id.bRegistration) public void bRegistrationClicked() {
    mRegistrationActivityPresenter.sendRegistrationData(mEditTextPhoneEmail.getText().toString());
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

  @Override public void startLoginActivity() {
    mNavigator.startActivity(this, new Intent(RegistrationActivity.this, LoginActivity.class));
    finish();
  }

  @Override public void showRegistrationDialog() {
    DialogFactory.createSimpleOkErrorDialog(this, R.string.attention,
        R.string.registration_dialog_content).show();
  }

  @Override public void showPB() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hidePB() {
    mProgressBar.setVisibility(View.GONE);
  }
}
