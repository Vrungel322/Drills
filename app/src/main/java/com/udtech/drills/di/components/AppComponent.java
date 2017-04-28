package com.udtech.drills.di.components;

import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.di.modules.AppModule;
import com.udtech.drills.feature.content.presenters.ContentActivityPresenter;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.start_point.presenters.MainActivityPresenter;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {

  //presenters
  void inject(MainActivityPresenter presenter);

  void inject(LoginActivityPresenter presenter);

  void inject(ContentActivityPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
}
