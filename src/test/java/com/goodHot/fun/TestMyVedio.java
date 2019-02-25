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
    public void test() throws IOException, UpException, InterruptedException {
        CoubEmbedMedia coubEmbedMedia = new CoubEmbedMedia(){{
            setVideoURL("https://coubsecure-s.akamaihd.net/get/b31/p/coub/simple/cw_file/8317e85c800/2d466b2c8d196ed592910/muted_mp4_med_size_1470742717_muted_med.mp4");
            setAudioURL("https://coubsecure-s.akamaihd.net/get/b59/p/coub/simple/cw_looped_audio/ea1bdb8a5b4/532cc029d7ccde9411f29/high_1470742774_high.mp3");
        }};
        processService.coubEmbed(coubEmbedMedia);
    }
}
