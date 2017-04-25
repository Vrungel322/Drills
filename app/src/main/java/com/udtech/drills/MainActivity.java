package com.udtech.drills;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainActivityView {
  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }
}
