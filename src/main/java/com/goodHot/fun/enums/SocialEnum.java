package com.goodHot.fun.enums;

public class SocialEnum {

    public enum Channel {
        WECHAT("微博"), QQ("QQ"), WEIBO("微博"), OFFICIAL("官方");

        private String name;

        Channel(String name) {
            this.name = name;
        }
    }

}
