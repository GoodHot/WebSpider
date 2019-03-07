package com.goodHot.fun.service;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface ParsevideoService {
    String parse(String url) throws UnirestException;
}
