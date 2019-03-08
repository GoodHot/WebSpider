package com.goodHot.fun.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "phantom")
public class PhantomConfig {

    /**
     * phantomjs 环境配置
     */
    private String env;

//    @Bean
//    public PhantomJSDriver getPhantomJs() {
//        //设置必要参数
//        DesiredCapabilities dcaps = new DesiredCapabilities();
//        //ssl证书支持
//        dcaps.setCapability("acceptSslCerts", true);
//        //截屏支持
////        dcaps.setCapability("takesScreenshot", true);
//        //css搜索支持
////        dcaps.setCapability("cssSelectorsEnabled", true);
//        //js支持
//        dcaps.setJavascriptEnabled(true);
//        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径，which/whereis phantomjs可以查看）
//        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, env);
//        //创建无界面浏览器对象
//        return new PhantomJSDriver(dcaps);
//    }

}
