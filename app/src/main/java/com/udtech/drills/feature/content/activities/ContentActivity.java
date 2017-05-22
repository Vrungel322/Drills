package com.udtech.drills.feature.content.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.feature.content.fragments.ContentFragment;
import com.udtech.drills.feature.content.presenters.ContentActivityPresenter;
import com.udtech.drills.feature.content.views.IContentActivityView;
import com.udtech.drills.feature.create_practice.fragments.CreatePracticeFragment;
import com.udtech.drills.feature.holoshenie_with_timer.fragments.HoloshenieWithTimerFragment;
import timber.log.Timber;

public class ContentActivity extends BaseActivity implements IContentActivityView {
  @InjectPresenter ContentActivityPresenter mContentActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_content);
    super.onCreate(savedInstanceState);
  }

  @Override public void showContentFragment() {
    mNavigator.addFragment(this, R.id.contentContainer, ContentFragment.newInstance());
  }

  @Override public void onBackPressed() {
    if (mNavigator.getCountBackStack(this) == 1) {
      mContentActivityPresenter.postToUpdate();
    }
    int index = getFragmentManager().getBackStackEntryCount();
    Fragment fr = getSupportFragmentManager().findFragmentById(R.id.contentContainer);
    if (fr instanceof CreatePracticeFragment){
      Timber.e("CreatePracticeFragment");
    }
    super.onBackPressed();
  }
}
