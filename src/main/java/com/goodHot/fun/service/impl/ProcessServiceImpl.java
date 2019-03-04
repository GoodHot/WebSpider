package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.PostConfig;
import com.goodHot.fun.conf.UpYunConfig;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.enums.MediaEnum;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.*;
import com.goodHot.fun.util.UpYunUtil;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private PostConfig postConfig;

    private Download download = new Download();

    private DecodeHandler decodeHandler = new CoubDecodeHandlerImplement();

    @Autowired
    private UpYunUtil upYunUtil;

    @Autowired
    private UpYunConfig upYunConfig;

    @Autowired
    private VedioWaterMark vedioWaterMark;

    @Autowired
    private PictureWaterMark pictureWaterMark;

    @Override
    public void mp4(MP4Media media) throws IOException, UpException, InterruptedException {
        String videoName = Encrypts.md5(media.getVedioUrl()) + MediaEnum.VIDEO.suffix;
        String posterName = Encrypts.md5(media.getPosterUrl()) + MediaEnum.JPEG.suffix;
        String videoPath = download(media.getVedioUrl(), videoName);
        String posterPath = download(media.getPosterUrl(), posterName);
        // 添加水印
        videoPath = vedioWaterMark.waterMarkByFFpemg(videoPath, "/Users/yanwenyuan/Downloads/JieMen.fun/jm.png", "/tmp");
        posterPath = pictureWaterMark.waterMarkByImageMagic(posterPath, "/Users/yanwenyuan/Downloads/JieMen.fun/jm.png", "/tmp");
        // 上传OSS服务器
        media.setVedioUrl(upYunUtil.upload(videoPath, upYunConfig.getBucket().mp4Path(videoName)));
        media.setPosterUrl(upYunUtil.upload(posterPath, upYunConfig.getBucket().jpegPath(posterName)));
    }

    @Override
    public void jpeg(JPEGMedia media) throws IOException, UpException, InterruptedException {
        String imgName = Encrypts.md5(media.getUrl()) + MediaEnum.JPEG.suffix;
        String imgPath = download(media.getUrl(), imgName);
        // 添加水印
        pictureWaterMark.waterMarkByImageMagic(imgPath, "/Users/yanwenyuan/Downloads/JieMen.fun/jm.png", "/tmp");
        media.setUrl(upYunUtil.upload(imgPath, upYunConfig.getBucket().jpegPath(imgName)));
    }

    @Override
    public void coubEmbed(CoubEmbedMedia media) throws IOException, UpException, InterruptedException {
        String videoName = Encrypts.md5(media.getVideoURL()) + MediaEnum.VIDEO.suffix;
        String audioName = Encrypts.md5(media.getAudioURL()) + MediaEnum.AUDIO.suffix;
        String videoPath = download(media.getVideoURL(), videoName);
        String audioPath = download(media.getAudioURL(), audioName);
        // 解码
        decodeHandler.decode(new File(videoPath));
        // 添加水印
        videoPath = vedioWaterMark.waterMarkByFFpemg(videoPath, "/Users/yanwenyuan/Downloads/JieMen.fun/jm.png", "/tmp");
        // 上传OSS服务器
        media.setVideoURL(upYunUtil.upload(videoPath, upYunConfig.getBucket().coubPath(videoName)));
        media.setAudioURL(upYunUtil.upload(audioPath, upYunConfig.getBucket().coubPath(audioName)));

    }

    private String download(String url, String fileName) {
        String filePath = downloadPath(fileName);
        try {
            download.downloadFromUrl(url, filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filePath;
    }

    private String downloadPath(String fileName) {
        return postConfig.getDownloadDir() + fileName;
    }
}
