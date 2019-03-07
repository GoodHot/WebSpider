package com.goodHot.fun.conf;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;

@Data
@Component
@ConfigurationProperties(prefix = "watermark")
@Slf4j
public class WatermarkConfig {
    // 是否添加水印
    Boolean active = true;

    // 图片水印

    Picture picture = new Picture();

    // 视频水印
    Vedio vedio = new Vedio();

    public String test() {
        return "";
    }


    @Data
    public static class Picture {
        @Value("classpath:watermark/jm.png")
        String watermarkPath;

        @Value("/tmp/")
        String outputDir;

        public String getWatermarkPath() {
            if (StringUtils.isEmpty(watermarkPath)) {
                try {
                    return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath() + "watermark/jm.png";
                } catch (FileNotFoundException e) {
                    log.error("没有配置 watermark.picture.watermarkPath ");
                }
            }
            return watermarkPath;
        }
    }

    @Data
    public static class Vedio {
        @Value("classpath:watermark/jm.png")
        String watermarkPath;

        @Value("/tmp/")
        String outputDir;

        public String getWatermarkPath() {
            if (StringUtils.isEmpty(watermarkPath)) {
                try {
                    return ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath() + "watermark/jm.png";
                } catch (FileNotFoundException e) {
                    log.error("没有配置 watermark.picture.watermarkPath ");
                }
            }
            return watermarkPath;
        }
    }
}
