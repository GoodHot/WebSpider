package com.goodHot.fun.service;

import com.goodHot.fun.util.upyun.com.upyun.UpException;

import java.io.IOException;

public interface UploadService {
    String uploadImage(byte[] bytes, String originaName) throws IOException, UpException;
}
