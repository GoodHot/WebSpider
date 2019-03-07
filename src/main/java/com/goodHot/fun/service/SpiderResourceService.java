package com.goodHot.fun.service;

import com.goodHot.fun.dto.rpc.IIILab;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface SpiderResourceService {
    IIILab getResource(String url);

    String getResourceByParsevideo(String url) throws UnirestException;
}
