package com.udtech.drills.data.local.mappers.show_history;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrungel on 18.05.17.
 */

public class HistoryDay {
  private List<GroupedPractices> alByDay;
  private String sDate;
  // TODO: 09.06.2017 sum this time, vmesto time per 4 weeks
  private double doubleTimeDay;
  private String sTimeDay;
  private Long dPracticesDate;
  private boolean isChecked;

  public boolean isChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }

  public HistoryDay() {
    alByDay = new ArrayList<>();
  }

  public String getStringDate() {
    return sDate;
  }

  public void setStringDate(String sDate) {
    this.sDate = sDate;
  }

  public String getStringTimeDay() {
    return sTimeDay;
  }

  private void setStringTimeDay(String sTimeDay) {
    this.sTimeDay = sTimeDay;
  }

  public double getDoubleTimeDay() {
    return doubleTimeDay;
  }

  public void setDoubleTimeDay(double doubleTimeDay) {
    this.doubleTimeDay = doubleTimeDay;
    setStringTimeDay(String.valueOf(doubleTimeDay));
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

  public List<GroupedPractices> getGroupsOfPractics() {
    return alByDay;
  }
}
