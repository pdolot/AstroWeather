package com.example.astroweather.util;

import org.joda.time.LocalDateTime;

import java.util.Date;

public class TimeUtil {

    public static String getTime(Long date) {
        LocalDateTime dateTime = new LocalDateTime(new Date(date));
        return dateTime.toString("HH:mm:ss");
    }

    public static String getDateTime(Long date) {
        LocalDateTime dateTime = new LocalDateTime(new Date(date));
        return dateTime.toString("dd-MM-yyyy HH:mm:ss");
    }

    public static String getDate(Long date) {
        LocalDateTime dateTime = new LocalDateTime(new Date(date));
        return dateTime.toString("dd-MM-yyyy");
    }

    public static String getDay(Long date) {
        LocalDateTime dateTime = new LocalDateTime(new Date(date));
        return dateTime.toString("dd");
    }

    public static String getMonth(Long date) {
        LocalDateTime dateTime = new LocalDateTime(new Date(date));
        return dateTime.toString("MM");
    }
}
