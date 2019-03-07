package com.goodHot.fun.service.impl;

import com.alibaba.fastjson.JSON;
import com.goodHot.fun.dto.rpc.Parsevideo;
import com.goodHot.fun.service.ParsevideoService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class ParsevideoServiceImpl implements ParsevideoService {

    @Override
    public String parse(String url) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://www.parsevideo.com/api.php?callback=jQuery112409801861220671877_1551842302002")
                .header("cache-control", "no-store, no-cache, must-revalidate,no-cache")
                .header("cf-ray", "4b391315bffb9396-SJC")
                .header("content-encoding", "br")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("date", "Thu, 07 Mar 2019 02:17:42 GMT")
                .header("expect-ct", "max-age=604800, report-uri=\"https://report-uri.cloudflare.com/cdn-cgi/beacon/expect-ct\"")
                .header("expires", "Thu, 19 Nov 1981 08:52:00 GMT")
                .header("pragma", "no-cache")
                .header("server", "cloudflare")
                .header("set-cookie", "PHPSESSID=8f983afc71ac2c025563495a2409899d; path=/")
                .header("status", "200")
                .header("vary", "Accept-Encoding")
                .header("x-powered-by", "PHP/7.2.7")
                .header("Postman-Token", "dcbdc312-8d56-4c9e-8c88-4633546bed70")
                .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"url\"\r\n\r\n " + url + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"hash\"\r\n\r\n 478d45cd38188398ccdd6c4a33bc929f\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                .asString();

        String jsonp = response.getBody();
        jsonp = jsonp.substring(jsonp.indexOf("(") + 1, jsonp.length() - 2);
        Parsevideo videos = JSON.parseObject(jsonp, Parsevideo.class);
        return videos.getVideo().get(0).getUrl();
    }

    public static void main(String[] args) {
        String jsonp = "jQuery112409801861220671877_1551842302002({\"pages\":0,\"total\":5,\"video\":[{\"url\":\"http:\\/\\/f.us.sinaimg.cn\\/000jPjmClx07s49PCQuY01041200jg7W0E010.mp4?label=mp4_ld&template=636x360.28.0&Expires=1551846586&ssig=7XPBHi4xSM&KID=unistore,video\",\"thumb\":\"\",\"desc\":\"STREAM_URL: \\u624e\\u9488\\u5415\\u62a4\\u58eb\\u7684\\u5fae\\u535a\\u89c6\\u9891\"},{\"url\":\"http:\\/\\/f.us.sinaimg.cn\\/003XB8EPlx07s49QET7q01041200s9XU0E010.mp4?label=mp4_hd&template=848x480.28.0&Expires=1551846586&ssig=YKo7frDIFe&KID=unistore,video\",\"thumb\":\"\",\"desc\":\"STREAM_URL_HD: \\u624e\\u9488\\u5415\\u62a4\\u58eb\\u7684\\u5fae\\u535a\\u89c6\\u9891\"},{\"url\":\"http:\\/\\/f.us.sinaimg.cn\\/000jPjmClx07s49PCQuY01041200jg7W0E010.mp4?label=mp4_ld&template=636x360.28.0&Expires=1551846586&ssig=7XPBHi4xSM&KID=unistore,video\",\"thumb\":\"\",\"desc\":\"MP4_SD_URL: \\u624e\\u9488\\u5415\\u62a4\\u58eb\\u7684\\u5fae\\u535a\\u89c6\\u9891\"},{\"url\":\"http:\\/\\/f.us.sinaimg.cn\\/003XB8EPlx07s49QET7q01041200s9XU0E010.mp4?label=mp4_hd&template=848x480.28.0&Expires=1551846586&ssig=YKo7frDIFe&KID=unistore,video\",\"thumb\":\"\",\"desc\":\"MP4_HD_URL: \\u624e\\u9488\\u5415\\u62a4\\u58eb\\u7684\\u5fae\\u535a\\u89c6\\u9891\"},{\"url\":\"http:\\/\\/f.us.sinaimg.cn\\/001eVFr9gx07s4awbSQf01041200njBc0E010.mp4?label=hevc_mp4_hd&template=848x480.32.0&Expires=1551846586&ssig=%2BD5qx6vllA&KID=unistore,video\",\"thumb\":\"\",\"desc\":\"HEVC_MP4_HD: \\u624e\\u9488\\u5415\\u62a4\\u58eb\\u7684\\u5fae\\u535a\\u89c6\\u9891\"}],\"status\":\"ok\"});";
        System.out.println();
    }
}
