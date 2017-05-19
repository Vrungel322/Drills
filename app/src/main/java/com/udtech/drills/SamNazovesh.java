package com.udtech.drills;

import com.udtech.drills.data.local.mappers.Mapper;
import com.udtech.drills.data.local.mappers.show_history.GroupedPractices;
import com.udtech.drills.data.local.mappers.show_history.HistoryDay;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimuch on 19.05.2017.
 */

public class SamNazovesh {

    public List<String> getListIdByDay (List<HistoryDay> historyDayList) {
        List<String> listIdByDay = new ArrayList<>();

        for (HistoryDay historyDay : historyDayList) {
            for (GroupedPractices groupedPractices : historyDay.getGroupsOfPractics()) {
                for (HistoryForSend historyForSend : groupedPractices.getList()) {
                    listIdByDay.add(historyForSend.getHistoryPracticsID());
                }
            }
        }
        return listIdByDay;
    }

    public List<String> getListIdByPractice (List<GroupedPractices> groupedPracticesList) {
        List<String> listIdByDay = new ArrayList<>();

        for (GroupedPractices groupedPractices : groupedPracticesList) {
            for (HistoryForSend historyForSend : groupedPractices.getList()) {
                listIdByDay.add(historyForSend.getHistoryPracticsID());
            }
        }
        return listIdByDay;
    }
}
