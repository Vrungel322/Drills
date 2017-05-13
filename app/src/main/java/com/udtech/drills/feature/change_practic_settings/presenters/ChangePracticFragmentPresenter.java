package com.udtech.drills.feature.change_practic_settings.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.udtech.drills.App;
import com.udtech.drills.base.BasePresenter;
import com.udtech.drills.data.DataManager;
import com.udtech.drills.data.local.mappers.PracticToPracticForSendMapper;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.login.User;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import com.udtech.drills.feature.change_practic_settings.views.IChangePracticFragmentView;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Subscription;

/**
 * Created by Vrungel on 13.05.2017.
 */
@InjectViewState
public class ChangePracticFragmentPresenter extends BasePresenter<IChangePracticFragmentView> {
  @Inject DataManager mDataManager;
  @Inject User mUser;

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  public void updateCurrentPractice(Practic practic) {
    ArrayList<PracticForSend> practicForSends = new ArrayList<>();
    practicForSends.add(new PracticToPracticForSendMapper().transform(practic));
    Subscription subscription =
        mDataManager.sendUserDataPractic(mUser.getAuthKey(), practicForSends)
            .compose(ThreadSchedulers.applySchedulers())
            .subscribe(booleanResponse -> {
              if (booleanResponse.code() == 200 && booleanResponse.body()){
                getViewState().openHoloshenieWithtimerAfterChange();
              }
            });
    addToUnsubscription(subscription);
  }
}
