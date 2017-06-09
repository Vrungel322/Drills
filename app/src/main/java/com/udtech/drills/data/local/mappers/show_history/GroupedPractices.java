package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.remote.send_user_data.HistoryForSend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrungel on 18.05.17.
 */

public class GroupedPractices {
    private List<HistoryForSend> alByPractice;
    private String practiceName;
    private double doubleTimePractice;
    private String sTimePractice;
    private Long dPracticesDateFirst;
    private Long dPracticesDateLast;
    private boolean isChecked;
    private int setsCount;

    public GroupedPractices() {
        alByPractice = new ArrayList<>();
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public double getDoubleTimePractice() {
        return doubleTimePractice;
    }

    public void setDoubleTimePractice(double doubleTimePractice) {
        this.doubleTimePractice = doubleTimePractice;
        setStringTimePractice(String.valueOf(doubleTimePractice));
    }

    public Long getPracticesDateFirst() {
        return dPracticesDateFirst;
    }

    public void setPracticeDateFirst(Long dPracticesDateFirst) {
        this.dPracticesDateFirst = dPracticesDateFirst;
    }

    public Long getPracticesDateLast() {
        return dPracticesDateLast;
    }

    public void setPracticeDateLast(Long dPracticesDateLast) {
        this.dPracticesDateLast = dPracticesDateLast;
    }

    public void addHistoryForSend(HistoryForSend list) {
        alByPractice.add(list);
    }

    public HistoryForSend getALByPractice(int i) {
        return alByPractice.get(i);
    }

    public void setSetsCount(int setsCount) {
        this.setsCount = setsCount;
    }

    public int getSetsCount() {
        return setsCount;
    }

    public List<HistoryForSend> getListHistoryForSend() {
        return alByPractice;
    }
}
