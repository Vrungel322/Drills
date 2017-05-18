package com.udtech.drills.data.local.mappers.show_history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by vrungel on 18.05.17.
 */

public class HistoryDay {
  private List<GroupedPractics> alByDate;
  private Date date;
  private int intTimeDay;
  private String sTimeDay;

  public HistoryDay() {
    alByDate = new ArrayList<>();
  }

  public int getIntTimeDay() {
    return intTimeDay;
  }

  public List<GroupedPractics> getAlByDate() {
    return alByDate;
  }

  public Date getDate() {
    return date;
  }

  public String getStringDate() {
    return date.toString();
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getStringTimeDay() {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    df.setTimeZone(tz);
    sTimeDay = df.format(new Date(intTimeDay));
    return sTimeDay;
  }

  public void setIntTimeDay(int totalTime) {
    this.intTimeDay = totalTime;
  }

  public void add(GroupedPractics list) {
    alByDate.add(list);
  }

  public GroupedPractics get(int i) {
    return alByDate.get(i);
  }

  public List<GroupedPractics> getsList() {
    return alByDate;
  }

  public int size() {
    return alByDate.size();
  }
}
