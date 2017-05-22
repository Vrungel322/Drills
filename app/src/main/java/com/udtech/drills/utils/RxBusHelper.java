package com.udtech.drills.utils;

import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import java.util.List;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {

  public static class SendDataToDb {
    public List<PracticForSend> mPracticForSends;

    public SendDataToDb(List<PracticForSend> practics) {
      mPracticForSends = practics;
    }
  }

  public static class ChangeTimerSettings {
    public int mode;

    public ChangeTimerSettings(int mode) {
      this.mode = mode;
    }
  }

  public static class SynchronizeData {
  }
}
