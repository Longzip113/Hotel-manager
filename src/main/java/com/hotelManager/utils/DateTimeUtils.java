package com.hotelManager.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {
    
    /**
     * get time for createAt
     * 
     * @return
     */
    public static Long getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now).getTime();
    }

    public static String convertDate(Long timeSTamp) {

        Date date = new Date(timeSTamp);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }
}
