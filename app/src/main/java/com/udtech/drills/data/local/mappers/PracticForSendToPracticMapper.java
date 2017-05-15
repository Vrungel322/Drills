package com.udtech.drills.data.local.mappers;

import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;

/**
 * Created by Vrungel on 15.05.2017.
 */

public class PracticForSendToPracticMapper implements Mapper<PracticForSend, Practic> {

  @Override public Practic transform(PracticForSend obj) throws RuntimeException {
    return new Practic(0, obj.getDryPracticsID(), obj.getDryPracticsName(),
        obj.getDryPracticsDate(), Double.valueOf(String.valueOf(obj.getDryPracticsTime())),
        obj.getDryPracticsFirstSignalDelay(), obj.getBoolIsRandPracticsFirstSignalDelay(),
        obj.getDryPracticsTimeBetweenSets(), obj.getBoolIsRandPracticsTimeBetweenSets(),
        obj.getDryPracticsSets(), obj.getDryPracticsDescription(), 1);
  }
}
