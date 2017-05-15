package com.udtech.drills.data.local.Db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import com.udtech.drills.data.local.mappers.PracticToCantentValueMapper;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.utils.Constants;
import com.udtech.drills.utils.Converters;
import com.udtech.drills.utils.ThreadSchedulers;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Vrungel on 15.05.2017.
 */

public class PracticHelper {
  private static final String TABLE_NAME = "Practic";
  private final DBHelper helper;

  public PracticHelper(DBHelper helper) {
    this.helper = helper;
    createTable();
  }

  public void createTable() {
    SQLiteDatabase db = helper.getWritableDatabase();
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
        + TABLE_NAME
        + " "
        + "("
        + Constants.Db.ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
        + Constants.Db.ID_IN_REMOTE_DB
        + " TEXT NOT NULL,"
        + Constants.Db.PRACTICS_ID
        + " TEXT,"
        + Constants.Db.PRACTICS_NAME
        + " TEXT,"
        + Constants.Db.PRACTICS_DATE
        + " TEXT,"
        + Constants.Db.PRACTICS_TIME
        + " TEXT,"
        + Constants.Db.PRACTICS_FIRST_SIGNAL_DELAY
        + " INTEGER,"
        + Constants.Db.BOOL_Is_RAND_PRACTICS_FIRST_SIGNAL_DELAY
        + " INTEGER,"
        + Constants.Db.PRACTICS_TIME_BETWEEN_SETS
        + " INTEGER,"
        + Constants.Db.BOOL_Is_RAND_PRACTICS_TIME_BETWEEN_SETS
        + " INTEGER,"
        + Constants.Db.PRACTICS_SETS
        + " INTEGER,"
        + Constants.Db.PRACTICS_DESCRIPTION
        + " TEXT, "
        + Constants.Db.USER_ID
        + " INTEGER,"
        + " UNIQUE ("
        + Constants.Db.PRACTICS_ID
        + ") ON CONFLICT REPLACE"
        + ")"
        + ";";
    db.execSQL(CREATE_TABLE);
  }

  public long insert(Practic practic) {
    SQLiteDatabase db = helper.getWritableDatabase();
    long id = db.insert(TABLE_NAME, null, new PracticToCantentValueMapper().transform(practic));
    return id;
  }

  public Observable<List<Practic>> getAllPractics() {
    String selectAll = "SELECT * FROM " + TABLE_NAME;
    getTableAsString(helper.getWritableDatabase(), TABLE_NAME);
    return select(selectAll);
  }

  @NonNull private Observable<List<Practic>> select(String query) {
    //        Log.e("helper", query);
    return Observable.create(subscriber -> {
      List<Practic> messages = synchronousSelect(query);

      subscriber.onNext(messages);
      subscriber.onCompleted();
    });
  }

  private List<Practic> synchronousSelect(String query) {
    List<Practic> practics = new ArrayList<>();
    SQLiteDatabase db = helper.getWritableDatabase();

    Cursor cursor = db.rawQuery(query, null);
    if (cursor != null) {
      while (cursor.moveToNext()) {
        practics.add(fetchPractic(cursor));
      }
      cursor.close();
    }
    return practics;
  }

  private Practic fetchPractic(Cursor cursor) {
    int id = Db.getInt(cursor, Constants.Db.ID_IN_REMOTE_DB);
    String practicsId = Db.getString(cursor, Constants.Db.PRACTICS_ID);
    String practicsName = Db.getString(cursor, Constants.Db.PRACTICS_NAME);
    String practicsDate = Db.getString(cursor, Constants.Db.PRACTICS_DATE);
    String practicsTime = Db.getString(cursor, Constants.Db.PRACTICS_TIME);
    int firstSetDelay = Db.getInt(cursor, Constants.Db.PRACTICS_FIRST_SIGNAL_DELAY);
    int boolIsRandPracticsFirstSignalDelay =
        Db.getInt(cursor, Constants.Db.BOOL_Is_RAND_PRACTICS_FIRST_SIGNAL_DELAY);
    int practicsTimeBetweenSets = Db.getInt(cursor, Constants.Db.PRACTICS_TIME_BETWEEN_SETS);
    int boolIsRandPracticsTimeBetweenSets =
        Db.getInt(cursor, Constants.Db.BOOL_Is_RAND_PRACTICS_TIME_BETWEEN_SETS);
    int practicsSets = Db.getInt(cursor, Constants.Db.PRACTICS_SETS);
    String practicsDescription = Db.getString(cursor, Constants.Db.PRACTICS_DESCRIPTION);
    int userId = Db.getInt(cursor, Constants.Db.USER_ID);
    return new Practic(id, practicsId, practicsName, Converters.stringToDouble(practicsDate),
        Converters.stringToDouble(practicsTime), firstSetDelay, boolIsRandPracticsFirstSignalDelay,
        practicsTimeBetweenSets, boolIsRandPracticsTimeBetweenSets, practicsSets,
        practicsDescription, userId);
  }

  public Observable<Practic> getPracticByPracticsId(String practicsId) {
    String selectAll =
        "SELECT * FROM " + TABLE_NAME + " WHERE " + Constants.Db.PRACTICS_ID + " = " + practicsId;
    return select(selectAll).filter(contacts -> contacts.size() == 1)
        .map(contacts -> contacts.get(0));
  }

  public List<Practic> getAllContactsSync() {
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
    Cursor c  = db.rawQuery("SELECT * FROM " + tableName, null);
    if (c.moveToFirst() ){
      String[] columnNames = c.getColumnNames();
      do {
        for (String name: columnNames) {
          tableString += String.format("%s: %s\n", name,
              c.getString(c.getColumnIndex(name)));
        }
        tableString += "\n";

      } while (c.moveToNext());
    }
    Log.e("DB_LOG", tableString);

    return tableString;
  }
}
