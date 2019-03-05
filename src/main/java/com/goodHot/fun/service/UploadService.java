package com.goodHot.fun.service;


import com.upyun.UpException;

import java.io.IOException;

public interface UploadService {
    String uploadImage(byte[] bytes, String originaName) throws IOException, UpException;
}
