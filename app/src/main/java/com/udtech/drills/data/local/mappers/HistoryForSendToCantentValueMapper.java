package com.udtech.drills.data.local.mappers;

import android.content.ContentValues;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.utils.Constants;

/**
 * Created by Vrungel on 17.05.2017.
 */

public class HistoryForSendToCantentValueMapper implements Mapper<HistoryForSend, ContentValues> {
  @Override public ContentValues transform(HistoryForSend obj) throws RuntimeException {
    ContentValues contentValues = new ContentValues();
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_ID, obj.getHistoryPracticsID());
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_NAME, obj.getHistoryPracticsName());
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_SETS, obj.getHistoryPracticsSets());
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_TIME, obj.getHistoryPracticsTime());
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_TYPE, obj.getObjectType());
    contentValues.put(Constants.DbHistory.HISTORY_PRACTIC_DATE, obj.getHistoryPracticsDate());
    return contentValues;
  }
}
