package com.example.android.models;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

/**
 * Created by 1712130027 on 10/05/2018.
 */

public class BasicTime{
    int hour;
    int minute;
    String formatedTime;

    public BasicTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.formatedTime = String.format("%02d", hour)+":"+String.format("%02d", minute);
    }


    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getFormatedTime() {
        return formatedTime;
    }

    public static List<BasicTime> divideTime(String sHr1, String sHr2, int interval){
        BasicTime time1 = stringToBasicTime(sHr1);
        BasicTime time2 = stringToBasicTime(sHr2);

        int time1Minutes = time1.getHour()*60 + time1.getMinute() + 20;
        int time2Minutes = time2.getHour()*60 + time2.getMinute() - 20;
        int minuteDif =  abs(time1Minutes - time2Minutes);

        int minuteInterval = minuteDif / (interval - 1); //

        List<BasicTime> separatedTimes = new ArrayList<>();

        separatedTimes.add(new BasicTime(time1Minutes / 60, time1Minutes % 60));

        /*CHECAR ESSA LÓGICA*/
        for(int i = 1 ; i < interval ; i++){
            int timeInMintes = time1Minutes + i*minuteInterval;
            separatedTimes.add(new BasicTime(timeInMintes / 60, timeInMintes % 60));
        }
        /*CHECAR ESSA LÓGICA*/

        return separatedTimes;
    }

    public static BasicTime stringToBasicTime(String hr){
        String[] hrSplit = hr.split(":");

        return new BasicTime(Integer.parseInt(hrSplit[0]), Integer.parseInt(hrSplit[1]));

    }
}
