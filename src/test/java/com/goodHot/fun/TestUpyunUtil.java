package com.goodHot.fun;

import com.goodHot.fun.conf.UpYunConfig;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.FileUtil;
import com.goodHot.fun.util.UpYunUtil;
import com.upyun.UpException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;


public class TestUpyunUtil extends BaseTestsuit {

    @Autowired
    private UpYunUtil upYunUtil;

    @Autowired
    private UpYunConfig upYunConfig;

    @Test
    public void uploadFile() throws IOException, UpException {
        byte[] img = FileUtil.toByteArrayNIO("/Users/yanwenyuan/Downloads/JieMen.fun/otter2_wm.jpg");
        File imgFile = new File("/Users/yanwenyuan/Downloads/JieMen.fun/otter2_wm.jpg");
        upYunUtil.upload("/Users/yanwenyuan/Downloads/JieMen.fun/otter2_wm.jpg", upYunConfig.getBucket().getJpeg() + "/" + imgFile.getName());
    }

    @Test
    public void uploadByte() throws IOException, UpException {
        byte[] img = FileUtil.toByteArrayNIO("/Users/yanwenyuan/Downloads/JieMen.fun/otter2_wm.jpg");
        File imgFile = new File("/Users/yanwenyuan/Downloads/JieMen.fun/otter2_wm.jpg");
        upYunUtil.upload(img, upYunConfig.getBucket().getJpeg() + "/" + imgFile.getName());
    }
}
