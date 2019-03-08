package com.goodHot.fun.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.goodHot.fun.dto.rpc.Parsevideo;
import com.goodHot.fun.service.ParsevideoService;
import com.goodHot.fun.service.PhantomService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ParsevideoServiceImpl implements ParsevideoService {

    @Autowired
    private PhantomService phantomService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PARSE_VIDEO_WEB_ENCRPTY = "PARSE_VIDEO_WEB_ENCRPTY";

    private static final Pattern pattern = Pattern.compile("var hash = \"(.+?)\"");

    @Override
    public String parse(String url) throws UnirestException {
        ParseVideoEncrpty encrpty = null;
        String json = redisTemplate.opsForValue().get(PARSE_VIDEO_WEB_ENCRPTY);
        if(StringUtils.isBlank(json)){
            encrpty = getEncrpty();
            redisTemplate.opsForValue().set(PARSE_VIDEO_WEB_ENCRPTY, JSON.toJSONString(encrpty), 1, TimeUnit.HOURS);
        } else {
            encrpty = JSON.parseObject(json, ParseVideoEncrpty.class);
        }
        HttpResponse<String> response = Unirest.post("https://www.parsevideo.com/api.php?callback=jQuery112409801861220671877_1551842302002")
                .header("cache-control", "no-store, no-cache, must-revalidate,no-cache")
                .header("cf-ray", encrpty.ray)
                .header("content-encoding", "br")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("date", "Thu, 07 Mar 2019 02:17:42 GMT")
                .header("expect-ct", "max-age=604800, report-uri=\"https://report-uri.cloudflare.com/cdn-cgi/beacon/expect-ct\"")
                .header("expires", "Thu, 19 Nov 1981 08:52:00 GMT")
                .header("pragma", "no-cache")
                .header("server", "cloudflare")
                .header("set-cookie", encrpty.cookie)
                .header("status", "200")
                .header("vary", "Accept-Encoding")
                .header("x-powered-by", "PHP/7.2.7")
                .header("Postman-Token", "dcbdc312-8d56-4c9e-8c88-4633546bed70")
                .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"url\"\r\n\r\n " + url + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"hash\"\r\n\r\n " + encrpty.hash + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                .asString();

        String jsonp = response.getBody();
        jsonp = jsonp.substring(jsonp.indexOf("(") + 1, jsonp.length() - 2);
        Parsevideo videos = JSON.parseObject(jsonp, Parsevideo.class);
        return videos.getVideo().get(0).getUrl();
    }

    private ParseVideoEncrpty getEncrpty(){
        ParseVideoEncrpty encrpty = new ParseVideoEncrpty();
        phantomService.get("https://www.parsevideo.com/", (webDriver -> {
            encrpty.hash = getHash(webDriver.getPageSource());
            encrpty.cookie = phantomService.packCookie(webDriver.manage().getCookies());
            HttpRequest req = HttpRequest.get("https://www.parsevideo.com/");
            encrpty.ray = req.header("cf-ray");
        }));
        return encrpty;
    }

    private String getHash(String source){
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    @Data
    public static class ParseVideoEncrpty {
        private String cookie;
        private String hash;
        private String ray;
    }

}
