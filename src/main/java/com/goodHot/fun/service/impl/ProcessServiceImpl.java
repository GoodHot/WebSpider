package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.UpYunConfig;
import com.goodHot.fun.conf.WatermarkConfig;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.enums.MediaEnum;
import com.goodHot.fun.service.DownloadService;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.*;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private CoubDecodeHandler decodeHandler;

    @Autowired
    private UpYunUtil upYunUtil;

    @Autowired
    private UpYunConfig upYunConfig;

    @Autowired
    private VedioUtil vedioUtil;

    @Autowired
    private PictureUtil pictureUtil;

    @Autowired
    private WatermarkConfig watermarkConfig;

    @Autowired
    private DownloadService downloadService;

    @Override
    public void mp4(MP4Media media) throws IOException, UpException, InterruptedException {
        String videoName = Encrypts.md5(media.getVideoUrl()) + MediaEnum.VIDEO.suffix;
        String posterName = Encrypts.md5(media.getPosterUrl()) + MediaEnum.JPEG.suffix;
        String videoPath = downloadService.syncDownloadForURL(media.getVideoUrl(), videoName);
        String posterPath = downloadService.syncDownloadForURL(media.getPosterUrl(), posterName);
        // 添加水印
        videoPath = vedioUtil.waterMarkByFFpemg(videoPath, watermarkConfig.getVedio().getWatermarkPath(), watermarkConfig.getVedio().getOutputDir());
        posterPath = pictureUtil.waterMarkByImageMagic(posterPath, watermarkConfig.getPicture().getWatermarkPath(), watermarkConfig.getPicture().getOutputDir());
        // 上传OSS服务器
        media.setVideoUrl(upYunUtil.upload(videoPath, upYunConfig.getBucket().mp4Path(videoName)));
        media.setPosterUrl(upYunUtil.upload(posterPath, upYunConfig.getBucket().jpegPath(posterName)));
    }

    @Override
    public void jpeg(JPEGMedia media) throws IOException, UpException, InterruptedException {
        String imgName = Encrypts.md5(media.getUrl()) + MediaEnum.JPEG.suffix;
        String imgPath = downloadService.syncDownloadForURL(media.getUrl(), imgName);
        // 添加水印
        pictureUtil.waterMarkByImageMagic(imgPath, watermarkConfig.getPicture().getWatermarkPath(), watermarkConfig.getPicture().getOutputDir());
        media.setUrl(upYunUtil.upload(imgPath, upYunConfig.getBucket().jpegPath(imgName)));
    }

    @Override
    public void coubEmbed(CoubEmbedMedia media) throws IOException, UpException, InterruptedException {
        String videoName = Encrypts.md5(media.getVideoURL()) + MediaEnum.VIDEO.suffix;
        String audioName = Encrypts.md5(media.getAudioURL()) + MediaEnum.AUDIO.suffix;
        String videoPath = downloadService.syncDownloadForURL(media.getVideoURL(), videoName);
        String audioPath = downloadService.syncDownloadForURL(media.getAudioURL(), audioName);
        // 解码
        decodeHandler.decode(new File(videoPath));
        // 添加水印
        videoPath = vedioUtil.waterMarkByFFpemg(videoPath, watermarkConfig.getVedio().getWatermarkPath(), watermarkConfig.getVedio().getOutputDir());
        // 上传OSS服务器
        media.setVideoURL(upYunUtil.upload(videoPath, upYunConfig.getBucket().coubPath(videoName)));
        media.setAudioURL(upYunUtil.upload(audioPath, upYunConfig.getBucket().coubPath(audioName)));

    }
}
