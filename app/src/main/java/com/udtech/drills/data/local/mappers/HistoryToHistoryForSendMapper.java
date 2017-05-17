package com.udtech.drills.data.local.mappers;

import com.udtech.drills.data.remote.fetch_user_data.History;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;

/**
 * Created by Vrungel on 17.05.2017.
 */

public class HistoryToHistoryForSendMapper implements Mapper<History, HistoryForSend> {
  @Override public HistoryForSend transform(History obj) throws RuntimeException {
    return new HistoryForSend(obj.getHistoryPracticsSets(), obj.getHistoryPracticsName(),
        obj.getHistoryPracticsTime(), obj.getHistoryPracticsID(), obj.getObjectType(),
        obj.getHistoryPracticsDate());
  }
}
