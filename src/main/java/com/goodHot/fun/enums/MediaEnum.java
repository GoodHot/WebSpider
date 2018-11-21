package com.goodHot.fun.enums;

public enum MediaEnum {
    GIF(10), JPG(20), TEXT(30), MP4(40), COUB(50);

    public Integer type;

    MediaEnum(Integer type) {
        this.type = type;
    }
}
