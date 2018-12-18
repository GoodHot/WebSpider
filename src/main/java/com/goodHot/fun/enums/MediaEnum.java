package com.goodHot.fun.enums;

public enum MediaEnum {
    GIF(10, ".gif"), JPEG(20, ".jpg"), TEXT(30, ""), VIDEO(40, ".mp4"), COUB(50, ".mp4"), COUB_EMBED(60, ".mp4"), AUDIO(70, ".mp3");

    public Integer type;
    public String suffix;

    MediaEnum(Integer type, String suffix) {
        this.type = type;
        this.suffix = suffix;
    }
}
