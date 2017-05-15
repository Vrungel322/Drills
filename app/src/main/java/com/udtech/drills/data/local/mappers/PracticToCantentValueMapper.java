package com.udtech.drills.data.local.mappers;

import android.content.ContentValues;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.utils.Constants;

/**
 * Created by Vrungel on 15.05.2017.
 */

public class PracticToCantentValueMapper implements Mapper<Practic, ContentValues> {
  @Override public ContentValues transform(Practic obj) throws RuntimeException {
    ContentValues contentValues = new ContentValues();
    contentValues.put(Constants.Db.ID_IN_REMOTE_DB, String.valueOf(obj.getId()));
    contentValues.put(Constants.Db.PRACTICS_ID, String.valueOf(obj.getDryPracticsID()));
    contentValues.put(Constants.Db.PRACTICS_NAME, obj.getDryPracticsName());
    contentValues.put(Constants.Db.PRACTICS_DATE, String.valueOf(obj.getDryPracticsDate()));
    contentValues.put(Constants.Db.PRACTICS_TIME, String.valueOf(obj.getDryPracticsTime()));
    contentValues.put(Constants.Db.PRACTICS_FIRST_SIGNAL_DELAY,
        obj.getDryPracticsFirstSignalDelay());
    contentValues.put(Constants.Db.BOOL_Is_RAND_PRACTICS_FIRST_SIGNAL_DELAY,
        obj.getBoolIsRandPracticsFirstSignalDelay());
    contentValues.put(Constants.Db.PRACTICS_TIME_BETWEEN_SETS, obj.getDryPracticsTimeBetweenSets());
    contentValues.put(Constants.Db.BOOL_Is_RAND_PRACTICS_TIME_BETWEEN_SETS,
        obj.getBoolIsRandPracticsTimeBetweenSets());
    contentValues.put(Constants.Db.PRACTICS_SETS, obj.getDryPracticsSets());
    contentValues.put(Constants.Db.PRACTICS_DESCRIPTION, obj.getDryPracticsDescription());
    contentValues.put(Constants.Db.USER_ID, obj.getUserId());
    return contentValues;
  }
}
