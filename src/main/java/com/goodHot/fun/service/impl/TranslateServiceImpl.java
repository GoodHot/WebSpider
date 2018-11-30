package com.goodHot.fun.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.conf.APIConfig;
import com.goodHot.fun.service.TranslateService;
import com.goodHot.fun.util.Encrypts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class TranslateServiceImpl implements TranslateService {

    @Autowired
    private APIConfig apiConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String translate(String query) {
        String salt = RandomUtils.nextInt(100000, 999999) + "";
        String md5 = Encrypts.md5(apiConfig.getBaidu().getAppId() + query +salt + "n7rDONXmDEK02bAmeJDI");
        String url = String.format(apiConfig.getBaidu().getTranslateURL(), query, apiConfig.getBaidu().getAppId(), salt, md5);
        ResponseEntity<String> out = restTemplate.getForEntity(url, String.class);
        JSONObject json = JSON.parseObject(out.getBody());
        log.info("[BAIDU translate API]Result：{}", json);
        if(!Objects.equals(json.getString("error_msg"), null)){
            log.error("[BAIDU translate API]Call fail, error: {}", json);
            return null;
        }
        String rst = json.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        return rst;
    }
}
