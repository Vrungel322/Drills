package com.udtech.drills.data.local.mappers.show_history;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by vrungel on 18.05.17.
 */

public class HistoryDay {
  private List<GroupedPractices> alByDay;
  private String sDate;
  private int intTimeDay;
  private String sTimeDay;
  private Long dPracticesDate;

  public HistoryDay() {
    alByDay = new ArrayList<>();
  }

  public String getStringDate() {
    return sDate;
  }

  public void setStringDate(String sDate) {
    Timber.e(sDate + "setStringDate");
    this.sDate = sDate;
  }

  public String getStringTimeDay() {
    return sTimeDay;
  }

  public void setStringTimeDay(String sTimeDay) {
    this.sTimeDay = sTimeDay;
  }

  public int getIntTimeDay() {
    return intTimeDay;
  }

  public void setIntTimeDay(int intTimeDay) {
    this.intTimeDay = intTimeDay;
    setStringTimeDay(String.valueOf(intTimeDay));
  }

  public Long getPracticeDate() {
    return dPracticesDate;
  }

  public void setPracticeDate(Long dPracticesDate) {
    this.dPracticesDate = dPracticesDate;
    setStringDate(String.valueOf(dPracticesDate));
  }

  public void addGroupedPractices(GroupedPractices list) {
    alByDay.add(list);
  }

  public GroupedPractices getALByDay(int i) {
    return alByDay.get(i);
  }

  public int sizeALByDay() {
    return alByDay.size();
  }

  public List<GroupedPractices> getList() {
      return alByDay;
  }
}
