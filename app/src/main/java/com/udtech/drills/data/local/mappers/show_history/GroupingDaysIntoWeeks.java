package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import com.udtech.drills.utils.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Dimuch on 22.05.2017.
 */

public class GroupingDaysIntoWeeks {

  private static final int NUMBER_OF_WEEKS = 4;
  private static final int DAYS_IN_WEEK = 7;

  public List<Integer> getListStatusOfDay(List<HistoryForSend> historyForSendList) {

    List<HistoryDay> historyDayList =
        new HistoryForSendToHistoryDayMapper().transform(historyForSendList);

    //for (HistoryDay historyDay : historyDayList) {
    //  Timber.e(Converters.dateFromSeconds(historyDay.getStringDate()) + " BD");
    //}

    Date firstDateFourWeeksAgo = getFirstDayFourWeeksAgo();
    //Timber.e(firstDateFourWeeksAgo.toString());

    List<Date> listAllDatePerFourWeeks = getListAllDatePerFourWeeks(firstDateFourWeeksAgo);

    //for (Date date : listAllDatePerFourWeeks) {
    //  Timber.e(date.toString() + " all date");
    //}

    List<HistoryDay> listHistoryDayPerFourWeeks =
        getListHistoryDayForFourWeeks(historyDayList, firstDateFourWeeksAgo);
    sortListHistoryDayPerFourWeeksAscendingOrder(listHistoryDayPerFourWeeks);

    //for (HistoryDay historyDay : listHistoryDayPerFourWeeks) {
    //  Timber.e(Converters.dateFromSeconds(historyDay.getStringDate()) + " per four week");
    //}

    return fillListStatusOfDay(listHistoryDayPerFourWeeks, listAllDatePerFourWeeks);
  }

  public List<Double> getListTotalTimeOfTheWeek(List<HistoryForSend> historyForSendList) {

    List<HistoryDay> historyDayList =
        new HistoryForSendToHistoryDayMapper().transform(historyForSendList);

    //for (HistoryDay historyDay : historyDayList) {
    //  Timber.e(Converters.dateFromSeconds(historyDay.getStringDate()) + " BD");
    //}

    Date firstDateFourWeeksAgo = getFirstDayFourWeeksAgo();
    //Timber.e(firstDateFourWeeksAgo.toString());

    List<List<Date>> listFourWeeks = getListFourWeeks(firstDateFourWeeksAgo);

    //for (Date date : listAllDatePerFourWeeks) {
    //  Timber.e(date.toString() + " all date");
    //}

    List<HistoryDay> listHistoryDayPerFourWeeks =
        getListHistoryDayForFourWeeks(historyDayList, firstDateFourWeeksAgo);
    sortListHistoryDayPerFourWeeksAscendingOrder(listHistoryDayPerFourWeeks);

    return fillListTotalTimeOfTheWeek(listHistoryDayPerFourWeeks, listFourWeeks);
  }

  private Date getFirstDayFourWeeksAgo() {
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    int dayInThisMonth =
        calendar.get(Calendar.DAY_OF_WEEK) + (NUMBER_OF_WEEKS - 1) * DAYS_IN_WEEK - 2;
    calendar.add(Calendar.DAY_OF_YEAR, -dayInThisMonth);
    return resettingTime(calendar.getTime());
  }

  private List<Date> getListAllDatePerFourWeeks(Date firstDateFourWeeksAgo) {
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(firstDateFourWeeksAgo);

    List<Date> listAllDatePerFourWeeks = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_WEEKS * DAYS_IN_WEEK; i++) {
      listAllDatePerFourWeeks.add(calendar.getTime());
      calendar.add(Calendar.DAY_OF_YEAR, 1);
    }

    return listAllDatePerFourWeeks;
  }

  private List<List<Date>> getListFourWeeks(Date firstDateFourWeeksAgo) {
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(firstDateFourWeeksAgo);

    List<List<Date>> listFourWeeks = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_WEEKS; i++) {
      List<Date> listDatesInOneWeek = new ArrayList<>();
      for (int j = 0; j < DAYS_IN_WEEK; j++) {
        listDatesInOneWeek.add(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
      }
      listFourWeeks.add(listDatesInOneWeek);
    }

    return listFourWeeks;
  }

  private List<HistoryDay> getListHistoryDayForFourWeeks(List<HistoryDay> historyDayList,
      Date firstDateFourWeeksAgo) {

    List<HistoryDay> listHistoryDayForFourWeeks = new ArrayList<>();

    for (HistoryDay historyDay : historyDayList) {
      Date datePractices = new Date(historyDay.getPracticeDate() * 1000);
      if (datePractices.after(firstDateFourWeeksAgo)) {
        listHistoryDayForFourWeeks.add(historyDay);
      }
    }
    return listHistoryDayForFourWeeks;
  }

  private void sortListHistoryDayPerFourWeeksAscendingOrder(
      List<HistoryDay> listHistoryDayPerFourWeeks) {
    Collections.sort(listHistoryDayPerFourWeeks,
        (o1, o2) -> (o1.getPracticeDate() > o2.getPracticeDate() ? 1 : -1));
  }

  private List<Integer> fillListStatusOfDay(List<HistoryDay> listHistoryDayForFourWeeks,
      List<Date> listAllDatePerFourWeeks) {
    List<Integer> listStatusOfDay = new ArrayList<>();

    for (Date date : listAllDatePerFourWeeks) {
      if (!listHistoryDayForFourWeeks.isEmpty()) {
        if (date.before(
            resettingTime(new Date(listHistoryDayForFourWeeks.get(0).getPracticeDate() * 1000)))) {
          listStatusOfDay.add(Constants.STATUS_HAS_NO_PRACTICE);
          //Timber.e("add Constants.STATUS_HAS_NO_PRACTICE");
        } else if (date.equals(
            resettingTime(new Date(listHistoryDayForFourWeeks.get(0).getPracticeDate() * 1000)))) {
          listStatusOfDay.add(Constants.STATUS_HAS_PRACTICE);
          //Timber.e("add Constants.STATUS_HAS_PRACTICE");
          listHistoryDayForFourWeeks.remove(0);
        }
      } else {
        if (date.before(new Date(System.currentTimeMillis()))) {
          listStatusOfDay.add(Constants.STATUS_HAS_NO_PRACTICE);
          //Timber.e("add Constants.STATUS_HAS_NO_PRACTICE");
        } else {
          listStatusOfDay.add(Constants.STATUS_DAY_HAS_NOT_COME);
        }
      }
    }

    return listStatusOfDay;
  }

  private List<Double> fillListTotalTimeOfTheWeek(List<HistoryDay> listHistoryDayPerFourWeeks,
      List<List<Date>> listFourWeeks) {
    List<Double> listTotalTimeOfTheWeek = new ArrayList<>();

    for (List<Date> listDateInOneWeek : listFourWeeks) {
      double totalTimePerOneWeek = 0;
      for (Date date : listDateInOneWeek) {
        for (HistoryDay historyDay : listHistoryDayPerFourWeeks) {
          if (date.equals(resettingTime(new Date(historyDay.getPracticeDate() * 1000)))) {
            totalTimePerOneWeek += historyDay.getDoubleTimeDay();
          }
        }
      }
      listTotalTimeOfTheWeek.add(totalTimePerOneWeek);
    }

    return listTotalTimeOfTheWeek;
  }

  private Date resettingTime(Date date) {
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }
}
