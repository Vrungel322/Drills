package com.udtech.drills.feature.create_practice.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.create_practice.views.ICreatePracticeFragmentView;
import com.udtech.drills.utils.Randomizer;
import com.udtech.drills.utils.RxBus;
import com.udtech.drills.utils.RxBusHelper;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by vrungel on 03.05.17.
 */
@InjectViewState public class CreatePracticeFragmentPresenter
    extends BasePresenter<ICreatePracticeFragmentView> {
  @Inject RxBus mRxBus;
  @Inject User mUser;
  @Inject DataManager mDataManager;
  private ArrayList<PracticForSend> mPracticForSends;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    mPracticForSends = new ArrayList<>();
  }

  public void sendData(String practiceName, String setTime, String delay, String betweenSets,
      String comments, int boolIsRandTimeBetweenSets, int boolisRandTimeBeforeFirstSignalDelay) {
    PracticForSend practic =
        new PracticForSend(practiceName, boolIsRandTimeBetweenSets, delay,
            Double.valueOf(String.valueOf(System.currentTimeMillis())), comments, 1,
            Integer.valueOf(setTime), boolisRandTimeBeforeFirstSignalDelay,
            Randomizer.randomString(32), betweenSets);
    mPracticForSends.add(practic);
    mRxBus.post(new RxBusHelper.SendDataToDb(mPracticForSends));
  }
}
