package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.PostConfig;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.enums.MediaEnum;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.CoubDecodeHandlerImplement;
import com.goodHot.fun.util.DecodeHandler;
import com.goodHot.fun.util.Download;
import com.goodHot.fun.util.Encrypts;
import com.goodHot.fun.util.upyun.UpYunUtil;
import com.goodHot.fun.util.upyun.com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    // 放在upyun的路径
    @Value("${upyun.bucket.coub}")
    private String upYunCoubPath;

    @Value("${upyun.bucket.jpeg}")
    private String upYunJPEGPath;

    @Value("${upyun.bucket.mp4}")
    private String upYunMP4Path;

    @Override
    public void mp4(MP4Media media) throws IOException, UpException {
        String videoPath = download(media.getUrl(), Encrypts.md5(media.getUrl()) + MediaEnum.VIDEO.suffix);
        String posterPath = download(media.getPosterUrl(), Encrypts.md5(media.getPosterUrl()) + MediaEnum.JPEG.suffix);
        // TODO: 2018/12/18 添加水印
        media.setUrl(upYunUtil.upload(videoPath, upYunMP4Path));
        media.setPosterUrl(upYunUtil.upload(posterPath, upYunMP4Path));

    }

    @Override
    public void jpeg(JPEGMedia media) throws IOException, UpException {
        String imgPath = download(media.getUrl(), Encrypts.md5(media.getUrl()) + MediaEnum.JPEG.suffix);
        // TODO: 2018/12/18 添加水印
        media.setUrl(upYunUtil.upload(imgPath, upYunJPEGPath));
    }

    @Override
    public void coubEmbed(CoubEmbedMedia media) throws IOException, UpException {
        String videoPath = download(media.getVideoURL(), Encrypts.md5(media.getVideoURL()) + MediaEnum.VIDEO.suffix);
        String audioPath = download(media.getAudioURL(), Encrypts.md5(media.getAudioURL()) + MediaEnum.AUDIO.suffix);
        // 解码
        decodeHandler.decode( new File(videoPath));
        // TODO: 2018/12/18 添加水印

        // 上传OSS服务器
        media.setVideoURL(upYunUtil.upload(videoPath, upYunCoubPath));
        media.setAudioURL(upYunUtil.upload(audioPath, upYunCoubPath));

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
