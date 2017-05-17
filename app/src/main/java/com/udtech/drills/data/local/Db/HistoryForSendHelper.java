package com.udtech.drills.data.local.Db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import com.udtech.drills.data.local.mappers.HistoryForSendToCantentValueMapper;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Vrungel on 17.05.2017.
 */

public class HistoryForSendHelper {
  private static final String TABLE_NAME = "History";
  private final DBHelper helper;

  public HistoryForSendHelper(DBHelper helper) {
    this.helper = helper;
    createTable();
  }

  public void createTable() {
    SQLiteDatabase db = helper.getWritableDatabase();
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME
        + " "
        + "("
        + Constants.DbHistory.ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
        + Constants.DbHistory.HISTORY_PRACTIC_ID
        + " TEXT NOT NULL,"
        + Constants.DbHistory.HISTORY_PRACTIC_NAME
        + " TEXT,"
        + Constants.DbHistory.HISTORY_PRACTIC_SETS
        + " INTEGER,"
        + Constants.DbHistory.HISTORY_PRACTIC_TIME
        + " INTEGER,"
        + Constants.DbHistory.HISTORY_PRACTIC_TYPE
        + " TEXT,"
        + Constants.DbHistory.HISTORY_PRACTIC_DATE
        + " TEXT,"
        + " UNIQUE ("
        + Constants.DbHistory.HISTORY_PRACTIC_ID
        + ") ON CONFLICT REPLACE"
        + ")"
        + ";";
    db.execSQL(CREATE_TABLE);
  }

  public long insert(HistoryForSend historyForSend) {
    SQLiteDatabase db = helper.getWritableDatabase();
    long id = db.insert(TABLE_NAME, null,
        new HistoryForSendToCantentValueMapper().transform(historyForSend));
    getTableAsString(db, TABLE_NAME);
    return id;
  }

  public Observable<List<HistoryForSend>> getAllHistory() {
    String selectAll = "SELECT * FROM " + TABLE_NAME;
    getTableAsString(helper.getWritableDatabase(), TABLE_NAME);
    return select(selectAll);
  }

  @NonNull private Observable<List<HistoryForSend>> select(String query) {
    //        Log.e("helper", query);
    return Observable.create(subscriber -> {
      List<HistoryForSend> messages = synchronousSelect(query);

      subscriber.onNext(messages);
      subscriber.onCompleted();
    });
  }

  private List<HistoryForSend> synchronousSelect(String query) {
    List<HistoryForSend> historyForSends = new ArrayList<>();
    SQLiteDatabase db = helper.getWritableDatabase();

    Cursor cursor = db.rawQuery(query, null);
    if (cursor != null) {
      while (cursor.moveToNext()) {
        historyForSends.add(fetchHistoryForSend(cursor));
      }
      cursor.close();
    }
    return historyForSends;
  }

  private HistoryForSend fetchHistoryForSend(Cursor cursor) {
    String historyId = Db.getString(cursor, Constants.DbHistory.HISTORY_PRACTIC_ID);
    String historyName = Db.getString(cursor, Constants.DbHistory.HISTORY_PRACTIC_NAME);
    Integer historySets = Db.getInt(cursor, Constants.DbHistory.HISTORY_PRACTIC_SETS);
    Integer historyTime = Db.getInt(cursor, Constants.DbHistory.HISTORY_PRACTIC_TIME);
    String historyType = Db.getString(cursor, Constants.DbHistory.HISTORY_PRACTIC_TYPE);
    Double practicsDate = Db.getDouble(cursor, Constants.DbHistory.HISTORY_PRACTIC_DATE);

    return new HistoryForSend(historySets, historyName, historyTime, historyId, historyType,
        practicsDate);
  }

  public Observable<List<HistoryForSend>> getHistiryForSendByDate(Double date) {
    String selectAll = "SELECT * FROM "
        + TABLE_NAME
        + " WHERE "
        + Constants.DbHistory.HISTORY_PRACTIC_DATE
        + " = "
        + date;
    return select(selectAll);
  }

  public List<HistoryForSend> getAllContactsSync() {
    String selectAll = "SELECT * FROM " + TABLE_NAME;
    return synchronousSelect(selectAll);
  }

  public void dropTableAndCreate() {
    SQLiteDatabase db = helper.getWritableDatabase();
    String dropTable = "DROP TABLE IF EXISTS " + " " + TABLE_NAME;
    db.execSQL(dropTable);
    createTable();
  }

  public static String getTableAsString(SQLiteDatabase db, String tableName) {
    Log.d("DB_LOG", "getTableAsString called");
    String tableString = String.format("Table %s:\n", tableName);
    Cursor c = db.rawQuery("SELECT * FROM " + tableName, null);
    if (c.moveToFirst()) {
      String[] columnNames = c.getColumnNames();
      do {
        for (String name : columnNames) {
          tableString += String.format("%s: %s\n", name, c.getString(c.getColumnIndex(name)));
        }
        tableString += "\n";
      } while (c.moveToNext());
    }
    Log.e("DB_LOG", tableString);

    return tableString;
  }
}
