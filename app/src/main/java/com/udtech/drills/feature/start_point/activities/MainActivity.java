package com.udtech.drills.feature.start_point.activities;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.start_point.presenters.MainActivityPresenter;
import com.udtech.drills.feature.start_point.views.IMainActivityView;

public class MainActivity extends BaseActivity implements IMainActivityView {
  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.tvBody) TextView mTextViewBody;
  private String TEST_EMAL_PHONE = "+380689647569";

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @OnClick(R.id.bLogin) public void bLoginClicked() {
    mainActivityPresenter.login("admin", "So3pqv+");
  }

  @OnClick(R.id.bSignUp) public void bSignUpClicked() {
    // каждый раз писать ноый емаил или телефон
    mainActivityPresenter.signUp(TEST_EMAL_PHONE);
  }

  @OnClick(R.id.bResetPass) public void bResetPassClicked() {
    mainActivityPresenter.resetPass(TEST_EMAL_PHONE);
  }

  @OnClick(R.id.bbFetchUserData) public void bFetchUserDataClicked() {
    mainActivityPresenter.fetchUserData();
  }

  @Override public void showBody(String body) {
    mTextViewBody.setText(body);
  }
}
