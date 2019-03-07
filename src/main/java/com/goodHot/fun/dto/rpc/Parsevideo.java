package com.goodHot.fun.dto.rpc;

import lombok.Data;

import java.util.List;

@Data
public class Parsevideo {

    private Integer pages;
    private Integer total;
    private List<Video> video;

    @Data
    public static class Video {
        private String url;
        private String thumb;
        private String desc;
    }

}
