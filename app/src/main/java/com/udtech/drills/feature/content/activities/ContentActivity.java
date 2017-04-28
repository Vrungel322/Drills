package com.udtech.drills.feature.content.activities;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.content.presenters.ContentActivityPresenter;
import com.udtech.drills.feature.content.views.IContentActivityView;

public class ContentActivity extends BaseActivity implements IContentActivityView {
  @InjectPresenter ContentActivityPresenter mContentActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_content);
    super.onCreate(savedInstanceState);
  }
}
