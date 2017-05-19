package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.remote.send_user_data.HistoryForSend;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by vrungel on 18.05.17.
 */

public class GroupedPractices {
  private List<HistoryForSend> alByPractice;
  private String practiceName;
  private int intTimePractice;
  private String sTimePractice;
  private Long dPracticesDateFirst;
  private Long dPracticesDateLast;

  public GroupedPractices() {
    alByPractice = new ArrayList<>();
  }

  public String getPracticeName() {
    return practiceName;
  }

  public void setPracticeName(String groupName) {
    this.practiceName = groupName;
  }

  public String getStringTimePractice() {
    return sTimePractice;
  }

  public void setStringTimePractice(String sTimePractice) {
    this.sTimePractice = sTimePractice;
  }

  public int getIntTimePractice() {
    return intTimePractice;
  }

  public void setIntTimePractice(int intTimePractice) {
    this.intTimePractice = intTimePractice;
    setStringTimePractice(String.valueOf(intTimePractice));
  }

  public Long getPracticesDateFirst() {
    return dPracticesDateFirst;
  }

  public void setPracticeDateFirst(Long dPracticesDateFirst) {
    this.dPracticesDateFirst = dPracticesDateFirst;
  }

  public Long getPracticesDateLast() {
    Timber.e(this.dPracticesDateFirst.toString() + " getPracticesDateLast");
    return dPracticesDateLast;
  }

  public void setPracticeDateLast(Long dPracticesDateLast) {
    this.dPracticesDateFirst = dPracticesDateLast;
    Timber.e(this.dPracticesDateFirst.toString() + " setPracticeDateLast");
  }

  public void addHistoryForSend(HistoryForSend list) {
    alByPractice.add(list);
  }

  public HistoryForSend getALByPractice(int i) {
    return alByPractice.get(i);
  }

  public int getSetsCount() {
    return alByPractice.size();
  }

  public List<HistoryForSend> getList() {
      return alByPractice;
  }
}
