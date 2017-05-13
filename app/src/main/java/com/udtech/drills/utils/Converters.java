package com.udtech.drills.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import timber.log.Timber;

/**
 * Created by Vrungel on 13.04.2017.
 */

public class Converters {

  public static String dateNow() {
    String dateResult;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault());
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    calendar.getTimeInMillis();
    dateResult = formatter.format(calendar.getTime());
    return dateResult;
  }

  public static String timeFromMilliseconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date);
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String timeFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dateFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd.MM.yyyy", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String fullDateWithTimeFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd.MM.yyyy (HH:mm)", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String dayFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter = new SimpleDateFormat("dd EEE", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static String detailDayFromSeconds(String date) {
    if (!date.equals("")) {
      String dateResult;
      SimpleDateFormat formatter =
          new SimpleDateFormat("dd MMMM (E)", java.util.Locale.getDefault());
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      long d = Long.valueOf(date) * 1000L;
      calendar.setTimeInMillis(d);
      dateResult = formatter.format(calendar.getTime());

      return dateResult;
    }
    return "";
  }

  public static int boolToInt(boolean b) {
    return b ? 1 : 0;
  }

  public static String milisToSecWithDecimal(long ms){
    final float sec;
    sec = ms / 1000.0f;
    return String.format("%.2f \nSEC", sec);
  }
}
