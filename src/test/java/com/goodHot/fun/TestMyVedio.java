package com.goodHot.fun;

import com.alibaba.fastjson.JSON;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.upyun.com.upyun.UpException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class TestMyVedio extends BaseTestsuit {

    @Autowired
    private ProcessService processService;

    @Test
    public void test() throws IOException, UpException {
        CoubEmbedMedia coubEmbedMedia = new CoubEmbedMedia("http://test.com");
        processService.coubEmbed(coubEmbedMedia);
    }
}
