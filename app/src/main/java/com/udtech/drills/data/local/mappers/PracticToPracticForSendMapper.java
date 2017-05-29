package com.udtech.drills.data.local.mappers;

import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.send_user_data.PracticForSend;

/**
 * Created by Vrungel on 13.05.2017.
 */

public class PracticToPracticForSendMapper implements Mapper<Practic, PracticForSend> {
  @Override public PracticForSend transform(Practic obj) throws RuntimeException {
    return new PracticForSend(obj.getDryPracticsName(), obj.getBoolIsRandPracticsTimeBetweenSets(),
        obj.getDryPracticsFirstSignalDelay(), obj.getDryPracticsDate(),
        obj.getDryPracticsDescription(), obj.getDryPracticsSets(),
        String.valueOf(obj.getDryPracticsTime()), obj.getBoolIsRandPracticsFirstSignalDelay(),
        obj.getDryPracticsID(), obj.getDryPracticsTimeBetweenSets());
  }
}
