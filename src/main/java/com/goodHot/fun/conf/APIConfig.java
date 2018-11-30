package com.goodHot.fun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "api")
public class APIConfig {

    private Baidu baidu;

    @Data
    public static class Baidu{
        private String translateURL;
        private String appId;
        private String sign;
    }

}
