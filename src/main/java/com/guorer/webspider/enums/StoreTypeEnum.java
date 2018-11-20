package com.guorer.webspider.enums;

public enum StoreTypeEnum {
    GIF(10), JPG(20), TEXT(30), MP4(40), COUB(50);

    public Integer type;

    StoreTypeEnum(Integer type) {
        this.type = type;
    }
}
