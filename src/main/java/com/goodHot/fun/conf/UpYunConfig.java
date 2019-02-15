package com.goodHot.fun.conf;

import com.goodHot.fun.util.Times;
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

        public String coubPath(String fileName){
            if (fileName.indexOf("/") == 0) {
                return coub + "/" + date() + fileName;
            }
            return coub + "/" + date() + "/" + fileName;
        }

        public String mp4Path(String fileName) {
            if (fileName.indexOf("/") == 0) {
                return mp4 + "/" + date() + fileName;
            }
            return mp4 + "/" + date() + "/" + fileName;
        }

        public String jpegPath(String fileName) {
            if (fileName.indexOf("/") == 0) {
                return jpeg + "/" + date() + fileName;
            }
            return jpeg + "/" + date() + "/" + fileName;
        }

        private String date() {
            return Times.format("yyyyMMdd", Times.now());
        }
    }

    @Data
    public static class Operator {
        private String name;
        private String pwd;
    }

}
