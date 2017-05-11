package com.udtech.drills.di.components;

import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.di.modules.AppModule;
import com.udtech.drills.feature.content.presenters.ContentActivityPresenter;
import com.udtech.drills.feature.content.presenters.ContentFragmentPresenter;
import com.udtech.drills.feature.create_practice.presenters.CreatePracticeFragmentPresenter;
import com.udtech.drills.feature.history.presenters.HistoryFragmentPresenter;
import com.udtech.drills.feature.holoshenie.presenters.HoloshenieFragmentPresenter;
import com.udtech.drills.feature.login.presenters.LoginActivityPresenter;
import com.udtech.drills.feature.registration.presenters.RegistrationActivityPresenter;
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

  void inject(RegistrationActivityPresenter presenter);

  void inject(ContentFragmentPresenter presenter);

  void inject(HoloshenieFragmentPresenter presenter);

  void inject(CreatePracticeFragmentPresenter presenter);

  void inject(HistoryFragmentPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
}
