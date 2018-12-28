package com.goodHot.fun.dto.rpc;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class IIILab {

    public static final IIILab fail() {
        IIILab result = new IIILab();
        result.retCode = 500;
        return result;
    }

    private Integer retCode;
    private String retDesc;
    private Boolean succ;
    private Data data;

    @lombok.Data
    @ToString
    static class Data {
        private String cover;
        private String text;
        private String video;
    }

}
