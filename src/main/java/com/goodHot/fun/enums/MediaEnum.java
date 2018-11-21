package com.goodHot.fun.enums;

public enum MediaEnum {
    GIF(10), JPEG(20), TEXT(30), VIDEO(40), COUB(50);

    public Integer type;

    MediaEnum(Integer type) {
        this.type = type;
    }
}
