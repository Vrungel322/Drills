package com.udtech.drills.utils;

/**
 * Created by Vrungel on 26.01.2017.
 */

public final class Constants {
  public class Remote {
    private static final String DOMEN = "the-gun-club.udtech.co";
    public static final String BASE_URL = "http://" + DOMEN + "/";
  }

  public static final int DELAY_TO_INVISIBILITY = 5000;

  public static final int DELAY_TIMER = 1;
  public static final int SET_TIMER = 2;

  public class Db {
    public static final String ID = "ID";
    public static final String ID_IN_REMOTE_DB = "ID_IN_REMOTE_DB";
    public static final String PRACTICS_ID = "PRACTICS_ID";
    public static final String PRACTICS_NAME = "PRACTICS_NAME";
    public static final String PRACTICS_DATE = "PRACTICS_DATE";
    public static final String PRACTICS_TIME = "PRACTICS_TIME";
    public static final String PRACTICS_FIRST_SIGNAL_DELAY = "PRACTICS_FIRST_SIGNAL_DELAY";
    public static final String BOOL_Is_RAND_PRACTICS_FIRST_SIGNAL_DELAY =
        "BOOL_Is_RAND_PRACTICS_FIRST_SIGNAL_DELAY";
    public static final String PRACTICS_TIME_BETWEEN_SETS = "PRACTICS_TIME_BETWEEN_SETS";
    public static final String BOOL_Is_RAND_PRACTICS_TIME_BETWEEN_SETS =
        "BOOL_Is_RAND_PRACTICS_TIME_BETWEEN_SETS";
    public static final String PRACTICS_SETS = "PRACTICS_SETS";
    public static final String PRACTICS_DESCRIPTION = "PRACTICS_DESCRIPTION";
    public static final String USER_ID = "USER_ID";
  }
}
