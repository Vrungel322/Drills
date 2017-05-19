package com.udtech.drills.data.local.mappers.show_history;

import com.udtech.drills.data.local.mappers.Mapper;
import com.udtech.drills.data.remote.fetch_user_data.Practic;
import com.udtech.drills.data.remote.send_user_data.HistoryForSend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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

        fillDayAndPracticeEssence();
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

    private void sortALDay() {
        Collections.sort(alDay,
                ((o1, o2) -> (o1.getPracticeDate() < o2.getPracticeDate() ? 1 : -1)));
        for (HistoryDay historyDay : alDay) {
            Collections.sort(historyDay.getList(),
                    ((o1, o2) -> (o1.getPracticeDate() < o2.getPracticeDate() ? 1 : -1)));
        }
    }

    private void fillDayAndPracticeEssence() {
        for (HistoryDay hdAlDay : alDay) {
            int timeDay = 0;
            for (int i = 0; i < hdAlDay.sizeALByDay(); i++) {
                int timePractice = 0;
                for (int j = 0; j < hdAlDay.getALByDay(i).sizeALByPractice(); j++) {
                    timePractice += hdAlDay.getALByDay(i).getALByPractice(j).getHistoryPracticsTime();
                    hdAlDay.getALByDay(i).setPracticeDate(hdAlDay.getALByDay(i).getALByPractice(j)
                            .getHistoryPracticsDate().longValue() / 1000);

                    hdAlDay.getALByDay(i).setPracticeName(hdAlDay.getALByDay(i).getALByPractice(j)
                            .getHistoryPracticsName());
                }
                hdAlDay.getALByDay(i).setIntTimePractice(timePractice);

                timeDay += hdAlDay.getALByDay(i).getIntTimePractice();
                hdAlDay.setPracticeDate(hdAlDay.getALByDay(i).getPracticeDate());
            }
            hdAlDay.setIntTimeDay(timeDay);
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
