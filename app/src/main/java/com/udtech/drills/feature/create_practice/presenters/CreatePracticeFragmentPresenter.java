package com.udtech.drills.feature.create_practice.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.create_practice.views.ICreatePracticeFragmentView;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import javax.inject.Inject;

/**
 * Created by vrungel on 03.05.17.
 */
@InjectViewState public class CreatePracticeFragmentPresenter
    extends BasePresenter<ICreatePracticeFragmentView> {
  @Inject RxBus mRxBus;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void sendData() {
    PracticForSend practic =
        new PracticForSend("Dry Practice Name 11", "0", 3, 1491815595.94008, "1491815595.94008", 1,
            3, "0", "FwUzomJwBQi4BvvNdVUD5r093Lj9Gwxq", 3);
    mRxBus.post(new RxBusHelper.SendDataToServer(practic));
  }
}
