package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.remote.send_user_data.HistoryForSend;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by vrungel on 18.05.17.
 */

public class GroupedPractics {
  private List<HistoryForSend> alByPractice;
  private String name;
  private int intTimePractice;
  private String sTimePractice;

  public GroupedPractics() {
    alByPractice = new ArrayList<>();
  }

  public List<HistoryForSend> getAlByPractice() {
    return alByPractice;
  }

  public String getName() {
    if (!alByPractice.isEmpty()) {
      name = alByPractice.get(0).getHistoryPracticsName();
      return name;
    }
    return null;
  }

  public String getStringTimePractice() {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    df.setTimeZone(tz);
    sTimePractice = df.format(new Date(intTimePractice));
    return sTimePractice;
  }

  public int getIntTimePractice() {
    return intTimePractice;
  }

  public void setTimePractice(int totalTime) {
    this.intTimePractice = totalTime;
  }

  public int getQuantityPractice() {
    return alByPractice.size();
  }

  public void add(HistoryForSend list) {
    alByPractice.add(list);
  }

  public HistoryForSend get(int i) {
    return alByPractice.get(i);
  }

  public int size() {
    return alByPractice.size();
  }
}
