package com.goodHot.fun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "upyun")
public class UpYunConfig {

    private Bucket bucket;

    private Operator operator;

    @Data
    public static class Bucket {
        private String name;
        private String coub;
        private String jpeg;
        private String mp4;
    }

    @Data
    public static class Operator {
        private String name;
        private String pwd;
    }

}
