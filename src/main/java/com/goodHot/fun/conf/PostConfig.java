package com.goodHot.fun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "post")
public class PostConfig {

    private String downloadDir;

    public String getDownloadPath(String fileName) {
        return this.downloadDir + fileName;
    }

}
