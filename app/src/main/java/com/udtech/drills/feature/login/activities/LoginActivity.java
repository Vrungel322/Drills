package com.udtech.drills.feature.login.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.login.views.ILoginActivityView;
import com.udtech.drills.utils.Constants;
import com.yqritc.scalablevideoview.ScalableVideoView;
import java.io.IOException;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

/**
 * Created by John on 26.04.2017.
 */

public class LoginActivity extends BaseActivity implements ILoginActivityView {

  @InjectPresenter LoginActivityPresenter mLoginActivityPresenter;

  @BindView(R.id.clParent) ConstraintLayout mConstraintLayoutParent;
  @BindView(R.id.evpVideo) ScalableVideoView mScalableVideoView;
  @BindView(R.id.etPhoneEmail) EditText mEditTextPhoneEmail;
  @BindView(R.id.etPassword) EditText mEditTextPassword;
  @BindView(R.id.tvHelp) TextView mTextViewHelp;
  @BindView(R.id.ivLogo) ImageView mImageViewLogo;
  @BindView(R.id.tvTitle1) TextView mTextViewTitle1;
  @BindView(R.id.tvTitle2) TextView mTextViewTitle2;
  @BindView(R.id.bLogin) Button mButtonLogin;
  @BindView(R.id.tvCreateAccount) TextView mTextViewCreateAccount;
  private Unregistrar mUnregistrar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);

    mUnregistrar = KeyboardVisibilityEvent.registerEventListener(this, isOpen -> {
      if (isOpen) {
        Toast.makeText(this, "visib", Toast.LENGTH_SHORT).show();
        mLoginActivityPresenter.cancelTimer();
      } else {
        Toast.makeText(this, "hide", Toast.LENGTH_SHORT).show();
        mLoginActivityPresenter.startCountingTimer(Constants.DELAY_TO_INVISIBILITY);
      }
    });
  }

  @Override protected void onStart() {
    super.onStart();
    playVideo();
    mLoginActivityPresenter.startCountingTimer(Constants.DELAY_TO_INVISIBILITY);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnregistrar.unregister();
  }

  @Override public void onBackPressed() {
    if (mButtonLogin.getText()
        .toString()
        .equalsIgnoreCase(getResources().getString(R.string.login))) {
      super.onBackPressed();
    } else {
      mButtonLogin.setText(R.string.login);
      mEditTextPhoneEmail.setText("");
      mEditTextPhoneEmail.setHint(getResources().getString(R.string.enter_email_password));
      mEditTextPassword.setVisibility(View.VISIBLE);
      mTextViewHelp.setVisibility(View.VISIBLE);
    }
  }

  @OnClick(R.id.bLogin) public void bLoginResetPassClicked() {
    if (mButtonLogin.getText()
        .toString()
        .equalsIgnoreCase(getResources().getString(R.string.login))) {
      mLoginActivityPresenter.login(mEditTextPhoneEmail.getText().toString(),
          mEditTextPassword.getText().toString());
    } else {
      mLoginActivityPresenter.resetPassword(mEditTextPhoneEmail.getText().toString());
    }
  }

  @OnClick(R.id.tvHelp) public void tvHelpClicked() {
    mEditTextPhoneEmail.setText("");
    mEditTextPhoneEmail.setHint(R.string.reset_hint);
    mEditTextPassword.setVisibility(View.INVISIBLE);
    mTextViewHelp.setVisibility(View.INVISIBLE);
    mButtonLogin.setText(R.string.reset);
  }

  @OnClick(R.id.clParent) public void clParentClicked() {
    mLoginActivityPresenter.allVisible();
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

  @Override public void allVisible() {
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mImageViewLogo);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTextViewTitle1);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTextViewTitle2);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mEditTextPassword);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mEditTextPhoneEmail);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mButtonLogin);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTextViewHelp);
    YoYo.with(Techniques.FadeIn).duration(1000).playOn(mTextViewCreateAccount);
  }

  @Override public void allGone() {
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mImageViewLogo);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mTextViewTitle1);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mTextViewTitle2);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mEditTextPassword);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mEditTextPhoneEmail);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mButtonLogin);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mTextViewHelp);
    YoYo.with(Techniques.FadeOut).delay(1000).duration(500).playOn(mTextViewCreateAccount);
  }
}
