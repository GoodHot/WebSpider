package com.goodHot.fun.service.impl;

import com.goodHot.fun.dto.rpc.IIILab;
import com.goodHot.fun.service.IIIlabService;
import com.goodHot.fun.service.ParsevideoService;
import com.goodHot.fun.service.SpiderResourceService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpiderResourceServiceImpl implements SpiderResourceService {

    @Autowired
    private IIIlabService iiIlabService;

    @Autowired
    private ParsevideoService parsevideoService;

    @Override
    public IIILab getResource(String url) {
        if (url.contains("douyin.com")) {
            return iiIlabService.douyin(url);
        } else if(url.contains("weibo.com")){
            return iiIlabService.weibo(url);
        }
        return IIILab.fail();
    }

    @Override
    public String getResourceByParsevideo(String url) throws UnirestException {
        return parsevideoService.parse(url);
    }

}
