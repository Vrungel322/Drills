package com.udtech.drills.feature.start_point.activities;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.udtech.drills.R;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.feature.start_point.presenters.MainActivityPresenter;
import com.udtech.drills.feature.start_point.views.IMainActivityView;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements IMainActivityView {
  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.tvBody) TextView mTextViewBody;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
  }

  @OnClick(R.id.bLogin)
  public void bLoginClicked(){
    mainActivityPresenter.login("admin", "So3pqv+");
  }

  @OnClick(R.id.bSignUp)
  public void bSignUpClicked(){
    // каждый раз писать ноый емаил или телефон
    mainActivityPresenter.signUp("morningscvvcxvxnfgjhghctarr241fghfgh2496@ukr.net");
  }

  @Override public void showBody(String body) {
    mTextViewBody.setText(body);

  }
}
