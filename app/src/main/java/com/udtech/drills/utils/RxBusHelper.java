package com.udtech.drills.utils;

import com.udtech.drills.data.remote.send_user_data.PracticForSend;
import java.util.List;

/**
 * Created by John on 26.01.2017.
 */

public final class RxBusHelper {

  public static class SendDataToServer {
    public List<PracticForSend> mPracticForSends;

    public SendDataToServer(List<PracticForSend> practics) {
      mPracticForSends = practics;
    }
  }
}
