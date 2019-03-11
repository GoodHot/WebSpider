package com.goodHot.fun;

import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.PictureWaterMark;
import com.goodHot.fun.util.VedioUtil;
import com.upyun.UpException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class TestProcessService extends BaseTestsuit {

    @Autowired
    private ProcessService processService;

    @Autowired
    private PictureWaterMark pictureWaterMark;

    @Autowired
    private VedioUtil vedioUtil;

    @Test
    public void testCoubEmbed() throws IOException, UpException, InterruptedException {
        CoubEmbedMedia coubEmbedMedia = new CoubEmbedMedia() {{
            setVideoURL("https://coubsecure-s.akamaihd.net/get/b31/p/coub/simple/cw_file/8317e85c800/2d466b2c8d196ed592910/muted_mp4_med_size_1470742717_muted_med.mp4");
            setAudioURL("https://coubsecure-s.akamaihd.net/get/b59/p/coub/simple/cw_looped_audio/ea1bdb8a5b4/532cc029d7ccde9411f29/high_1470742774_high.mp3");
        }};
        processService.coubEmbed(coubEmbedMedia);
    }

    @Test
    public void testMp4() {
        MP4Media mp4Media = new MP4Media(){{
            setVideoUrl("");
            setPosterUrl("");
        }};
    }

    @Test
    public void testPictureWaterMark() throws IOException, InterruptedException {
        pictureWaterMark.waterMarkByImageMagic("/Users/yanwenyuan/Downloads/JieMen.fun/otter2.jpg",
                "/Users/yanwenyuan/Downloads/JieMen.fun/jm.png",
                "/Users/yanwenyuan/Downloads/JieMen.fun/");
    }

    @Test
    public void testVedioWaterMark() throws IOException, InterruptedException {
        vedioUtil.waterMarkByFFpemg("/Users/yanwenyuan/Downloads/ffmpeg/likeS.mp4",
                "/Users/yanwenyuan/Downloads/ffmpeg/dou.gif",
                "/Users/yanwenyuan/Downloads/ffmpeg/");
    }

    @Test
    public void testMp4CoverByFFpemg() throws IOException, InterruptedException {
        vedioUtil.vedioCoverByFFpemg(
                "/Users/yanwenyuan/Downloads/ffmpeg/likeS.mp4",
                "00:00:00",
                "/Users/yanwenyuan/Downloads/ffmpeg"
        );
    }
}
