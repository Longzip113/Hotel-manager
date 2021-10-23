package com.hotelManager.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}
