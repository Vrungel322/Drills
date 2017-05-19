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

import timber.log.Timber;

/**
 * Created by vrungel on 18.05.17.
 */

public class HistoryForSendToHistoryDayMapper implements Mapper<List<HistoryForSend>, List<HistoryDay>> {

    private List<HistoryDay> alDay;

    public HistoryForSendToHistoryDayMapper() {
        alDay = new ArrayList<>();
    }

    @Override
    public List<HistoryDay> transform(List<HistoryForSend> alHistoryForSend)
            throws RuntimeException {

        getALDay(alHistoryForSend);

        sortHistoryForSendInPractices();
        fillPracticeEssence();
        sortGroupPracticesInALDay();
        fillDayPractice();
        sortALDay();

        return alDay;
    }

    private Object getALDay(List<HistoryForSend> alHistoryForSend) {
        Map<Date, List<HistoryForSend>> mapEssenseByDate = getMapEssenseByDate(alHistoryForSend);
        //        mapEssenseByDate.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));

        for (List<HistoryForSend> listByDate : mapEssenseByDate.values()) {
            Map<String, List<HistoryForSend>> mapEssenseByPractice = getMapEssenseByType(listByDate);

            HistoryDay alByDay = new HistoryDay();
            for (List<HistoryForSend> listByPractice : mapEssenseByPractice.values()) {
                GroupedPractices alByPractice = new GroupedPractices();
                for (HistoryForSend essence : listByPractice) {
                    alByPractice.addHistoryForSend(essence);
                }
                alByDay.addGroupedPractices(alByPractice);
            }
            alDay.add(alByDay);
        }
        return alDay;
    }

    private void sortHistoryForSendInPractices() {
        for (HistoryDay historyDay : alDay) {
            for (GroupedPractices groupedPractices : historyDay.getGroupsOfPractics()) {
                Collections.sort(groupedPractices.getList(), ((o1, o2) ->
                        (o1.getHistoryPracticsDate() < o2.getHistoryPracticsDate() ? 1 : -1)));
            }
        }
    }

    private void sortGroupPracticesInALDay() {
//        for (HistoryDay historyDay : alDay) {
//            Collections.sort(historyDay.getGroupsOfPractics(),
//                    ((o1, o2) -> (o1.getPracticesDateLast() < o2.getPracticesDateLast() ? 1 : -1)));
//        }
    }

    private void sortALDay() {
//        Collections.sort(alDay,
//                ((o1, o2) -> (o1.getPracticeDate() < o2.getPracticeDate() ? 1 : -1)));
    }

    private void fillPracticeEssence() {
        for (int i = 0; i < alDay.size(); i++) {
            for (int j = 0; j < alDay.get(i).sizeALByDay(); j++) {
                int totalTimePractice = 0;
                for (int k = 0; k < alDay.get(i).getALByDay(j).getSetsCount(); k++) {
                    totalTimePractice += alDay.get(i).getALByDay(j).getALByPractice(k).getHistoryPracticsTime();
                }
                Timber.e(String.valueOf(alDay.get(i).getALByDay(j)
                        .getALByPractice(alDay.get(i).getALByDay(j).getSetsCount() - 1)
                        .getHistoryPracticsDate()
                        .longValue() / 1000) + "-------------------");
                alDay.get(i).getALByDay(j).setPracticeDateFirst(alDay.get(i).getALByDay(j)
                        .getALByPractice(alDay.get(i).getALByDay(j).getSetsCount() - 1)
                        .getHistoryPracticsDate()
                        .longValue() / 1000);
                Timber.e(String.valueOf(alDay.get(i).getALByDay(j)
                        .getALByPractice(0)
                        .getHistoryPracticsDate()
                        .longValue() / 1000) + "*******************");
                alDay.get(i).getALByDay(j).setPracticeDateLast(alDay.get(i).getALByDay(j)
                        .getALByPractice(0)
                        .getHistoryPracticsDate()
                        .longValue() / 1000);
                alDay.get(i).getALByDay(j).setPracticeName(alDay.get(i).getALByDay(j)
                        .getALByPractice(0)
                        .getHistoryPracticsName());
                alDay.get(i).getALByDay(j).setIntTimePractice(totalTimePractice);
            }
        }
    }

    private void fillDayPractice() {
        for (int i = 0; i < alDay.size(); i++) {
            int totalTimeDay = 0;
            for (int j = 0; j < alDay.get(i).sizeALByDay(); j++) {
                totalTimeDay += alDay.get(i).getALByDay(j).getIntTimePractice();
            }
            Timber.e(String.valueOf(alDay.get(i).getALByDay(0)
                    .getPracticesDateLast()) + "++++++++++++++++++");
            alDay.get(i).setPracticeDate(alDay.get(i).getALByDay(0)
                    .getPracticesDateLast());
            alDay.get(i).setIntTimeDay(totalTimeDay);
        }
    }

    private Map<Date, List<HistoryForSend>> getMapEssenseByDate(
            List<HistoryForSend> alHistoryForSend) {
        Map<Date, List<HistoryForSend>> map = new HashMap<>();
        for (HistoryForSend essence : alHistoryForSend) {
            Date date = getDate(essence.getHistoryPracticsDate().longValue());
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
            String type = essence.getHistoryPracticsName();
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

    private Date getDate(Long dDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dDate.longValue());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
