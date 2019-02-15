package com.goodHot.fun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Times {

    public static final String FULL_DATE = "yyyy-MM-dd";

    public static final String FULL_TIME = "HH:mm:ss";

    public static final String FULL_DATE_TIME = FULL_DATE + " " + FULL_TIME;


    public static final Date now() {
        return new Date();
    }

    public static final Date timeForMS(Long ms) {
        return new Date(ms * 1000);
    }

    public static final String format(String fmt, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        return format.format(date);
    }

}
