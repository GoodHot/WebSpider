package com.goodHot.fun.util;

import java.util.Date;

public class Times {

    public static final Date now(){
        return new Date();
    }

    public static final Date timeForMS(Long ms){
        return new Date(ms * 1000);
    }

}
