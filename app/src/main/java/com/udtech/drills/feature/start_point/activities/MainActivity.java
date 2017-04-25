package com.udtech.drills.feature.start_point.activities;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.start_point.presenters.MainActivityPresenter;
import com.udtech.drills.feature.start_point.views.IMainActivityView;

public class MainActivity extends BaseActivity implements IMainActivityView {
  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }
}
