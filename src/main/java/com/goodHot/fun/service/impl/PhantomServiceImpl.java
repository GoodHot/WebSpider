package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.PhantomConfig;
import com.goodHot.fun.service.PhantomService;
import com.goodHot.fun.service.callback.PhantomCallback;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PhantomServiceImpl implements PhantomService {

    @Autowired
    private PhantomConfig phantomConfig;

    @Override
    public String getWebCookie(String url) {
        PhantomJSDriver webDriver = getPhantomJs();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.get(url);
        String cookie = packCookie(webDriver.manage().getCookies());
        webDriver.close();
        webDriver.quit();
        return cookie;
    }

    @Override
    public void get(String url, PhantomCallback run) {
        log.info("打开phantomJS");
        PhantomJSDriver webDriver = getPhantomJs();
        webDriver.get(url);
        run.run(webDriver);
        webDriver.close();
        webDriver.quit();
        webDriver = null;
        log.info("关闭phantomJS");
    }

    @Override
    public String packCookie(Set<Cookie> cookies) {
        StringBuilder result = new StringBuilder();
        cookies.forEach(c -> {
            result.append(c.getName()).append("=").append(c.getValue()).append("; ");
        });
        return result.toString();
    }

    private PhantomJSDriver getPhantomJs() {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
//        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
//        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径，which/whereis phantomjs可以查看）
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomConfig.getEnv());
        //创建无界面浏览器对象
        return new PhantomJSDriver(dcaps);
    }
}
