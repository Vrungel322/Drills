package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.local.mappers.Mapper;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vrungel on 18.05.17.
 */

public class HistoryForSendToHistoryDayMapper implements Mapper<List<HistoryForSend>, List<HistoryDay>> {

  private List<HistoryDay> alDay;

  public HistoryForSendToHistoryDayMapper() {
    alDay = new ArrayList<>();
  }

  @Override public List<HistoryDay> transform(List<HistoryForSend> alHistoryForSend)
      throws RuntimeException {
    Map<Date, List<HistoryForSend>> mapEssenseByDate = getMapEssenseByDate(alHistoryForSend);
    //        mapEssenseByDate.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));

    for (List<HistoryForSend> listByDate : mapEssenseByDate.values()) {
      Map<String, List<HistoryForSend>> mapEssenseByPractice = getMapEssenseByType(listByDate);

      HistoryDay alByDay = new HistoryDay();
      for (List<HistoryForSend> listByPractice : mapEssenseByPractice.values()) {
        GroupedPractics alByPractice = new GroupedPractics();
        for (HistoryForSend essence : listByPractice) {
          alByPractice.add(essence);
        }
        alByDay.add(alByPractice);
      }
      alDay.add(alByDay);
    }

    fillDayAndPracticeEssence();
    sortALDay();
    return alDay;
  }

  private void sortALDay() {
    Collections.sort(alDay, ((o1, o2) -> (o1.getDate().before(o2.getDate()) ? 1 : -1)));
  }

  private void fillDayAndPracticeEssence() {
    for (HistoryDay anAlDay : alDay) {
      int timeDay = 0;
      for (int j = 0; j < anAlDay.size(); j++) {
        int timePractice = 0;
        for (int k = 0; k < anAlDay.get(j).size(); k++) {
          timePractice += anAlDay.get(j).get(k).getHistoryPracticsTime();
          if (anAlDay.getDate() == null) anAlDay.setDate(getDate(anAlDay.get(j).get(k)));
        }
        anAlDay.get(j).setTimePractice(timePractice);

        timeDay += anAlDay.get(j).getIntTimePractice();
      }
      anAlDay.setIntTimeDay(timeDay);
    }
  }

  private Map<Date, List<HistoryForSend>> getMapEssenseByDate(
      List<HistoryForSend> alHistoryForSend) {
    Map<Date, List<HistoryForSend>> map = new HashMap<>();
    for (HistoryForSend essence : alHistoryForSend) {
      Date date = getDate(essence);
      //            System.out.println(date.toString());
      if (map.containsKey(date)) {
        map.get(date).add(essence);
      } else {
        List<HistoryForSend> essences = new ArrayList<>();
        essences.add(essence);
        map.put(date, essences);
      }
    }
    return map;
  }

  private Map<String, List<HistoryForSend>> getMapEssenseByType(
      List<HistoryForSend> alHistoryForSend) {
    Map<String, List<HistoryForSend>> map = new HashMap<>();
    for (HistoryForSend essence : alHistoryForSend) {
      String type = getType(essence);
      //            System.out.println(date.toString());
      if (map.containsKey(type)) {
        map.get(type).add(essence);
      } else {
        List<HistoryForSend> essences = new ArrayList<>();
        essences.add(essence);
        map.put(type, essences);
      }
    }
    return map;
  }

  private Date getDate(HistoryForSend essence) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(essence.getHistoryPracticsDate().longValue());
    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  private String getType(HistoryForSend essence) {
    return essence.getHistoryPracticsName();
  }
}
