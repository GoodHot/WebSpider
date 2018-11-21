package com.goodHot.fun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spider")
public class SpiderConfig {

    private Gag gag;
    private Coub coub;

    @Data
    public static class Gag {
        private String url;
    }

    @Data
    public static class Coub {
        private String url;
    }

}
