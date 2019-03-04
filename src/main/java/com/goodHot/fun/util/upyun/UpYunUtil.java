package com.goodHot.fun.util.upyun;

import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.util.upyun.com.UpYun;
import com.goodHot.fun.util.upyun.com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class UpYunUtil {
    @Autowired
    private UpYun upYun;

    /**
     * 返回 上传到upyun的地址
     *
     * @param localFilePath 本地文件路径
     * @param upYunPath     upyun存储路径
     * @return
     * @throws IOException
     * @throws UpException
     */
    public String upload(String localFilePath, String upYunPath) throws IOException, UpException {
        File file = new File(localFilePath);
        upYun.setContentMD5(UpYun.md5(file));
        Boolean isUploadOk = upYun.writeFile(upYunPath, file, true);
        if (isUploadOk) {
            return upYunPath;
        } else {
            ExceptionHelper.param(!isUploadOk, "上传upyun失败: {}", localFilePath);
        }
        return null;
    }

    /**
     * @param datas     文件流
     * @param upYunPath upyun存储路径
     * @return
     * @throws IOException
     * @throws UpException
     */
    public String upload(byte[] datas, String upYunPath) throws IOException, UpException {
        upYun.setContentMD5(UpYun.md5(upYunPath));
        Boolean isUploadOk = upYun.writeFile(upYunPath, datas, true);
        if (isUploadOk) {
            return upYunPath;
        } else {
            ExceptionHelper.param(!isUploadOk, "上传upyun失败: {}", upYunPath);
            return null;
        }
    }
}
