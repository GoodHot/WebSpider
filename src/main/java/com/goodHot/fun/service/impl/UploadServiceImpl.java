package com.goodHot.fun.service.impl;

import com.goodHot.fun.service.UploadService;
import com.goodHot.fun.util.Encrypts;
import com.goodHot.fun.util.UpYunUtil;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UpYunUtil upYunUtil;

    @Override
    public String uploadImage(byte[] datas, String originaName) throws IOException, UpException {
        String suffix = originaName.substring(originaName.lastIndexOf("."), originaName.length());
        String path = "/image/" + Encrypts.md5(datas) + suffix;
        return upYunUtil.upload(datas, path);
    }
}
