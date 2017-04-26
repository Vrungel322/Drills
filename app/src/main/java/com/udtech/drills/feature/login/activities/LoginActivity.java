package com.udtech.drills.feature.login.activities;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.login.views.ILoginActivityView;

/**
 * Created by John on 26.04.2017.
 */

public class LoginActivity extends BaseActivity implements ILoginActivityView {

  @InjectPresenter LoginActivityPresenter mLoginActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_login);
    super.onCreate(savedInstanceState);
  }
}
