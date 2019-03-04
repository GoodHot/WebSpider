package com.goodHot.fun.util.upyun;

import com.goodHot.fun.util.Encrypts;
import com.goodHot.fun.util.Times;
import org.apache.commons.lang3.RandomUtils;

public class Names {

    public static final String randomUpYunFileName(){
        return date() + "/" + Encrypts.md5(System.currentTimeMillis() + "_" + RandomUtils.nextInt());
    }

    public static final String randomUpYunFileName(String orgName){
        String suffix = orgName.substring(orgName.lastIndexOf("."), orgName.length());
        return "/" + date() + "/" + Encrypts.md5(System.currentTimeMillis() + "_" + RandomUtils.nextInt()) + suffix;
    }

    private static String date() {
        return Times.format("yyyyMMdd", Times.now());
    }

}
