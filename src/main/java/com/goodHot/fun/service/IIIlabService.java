package com.goodHot.fun.service;

import com.goodHot.fun.dto.rpc.IIILab;

public interface IIIlabService {
    IIILab douyin(String url);

    IIILab weibo(String url);
}
