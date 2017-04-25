package com.udtech.drills.di.components;

import com.udtech.drills.MainActivityPresenter;
import com.udtech.drills.base.BaseActivity;
import com.udtech.drills.base.BaseFragment;
import com.udtech.drills.di.modules.AppModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Vrungel on 25.01.2017.
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {

  //presenters
  void inject(MainActivityPresenter presenter);

  //activities
  void inject(BaseActivity activity);

  //fragments
  void inject(BaseFragment fragment);

  //adapters
}
