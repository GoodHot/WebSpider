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

    @Override
    public void mp4(MP4Media media) {
        String videoPath = download(media.getUrl(), Encrypts.md5(media.getUrl()) + MediaEnum.VIDEO.suffix);
        String posterPath = download(media.getPosterUrl(), Encrypts.md5(media.getPosterUrl()) + MediaEnum.VIDEO.suffix);
        // TODO: 2018/12/18 上传OSS服务器
        String ossVideoURL = videoPath;
        String ossPosterURL = posterPath;
        media.setUrl(ossVideoURL);
        media.setPosterUrl(ossPosterURL);
    }

    @Override
    public void jpeg(JPEGMedia media) {
        String imgPath = download(media.getUrl(), Encrypts.md5(media.getUrl()) + MediaEnum.VIDEO.suffix);
        // TODO: 2018/12/18 上传OSS服务器
        String ossImgURL = imgPath;
        media.setUrl(ossImgURL);
    }

    @Override
    public void coubEmbed(CoubEmbedMedia media) {
        String videoPath = download(media.getVideoURL(), Encrypts.md5(media.getVideoURL()) + MediaEnum.VIDEO.suffix);
        String audioPath = download(media.getAudioURL(), Encrypts.md5(media.getAudioURL()) + MediaEnum.AUDIO.suffix);
        // TODO: 2018/12/18 上传OSS服务器
        decodeHandler.decode(new File(videoPath));
        String ossVideoURL = videoPath;
        String ossAudioURL = audioPath;
        media.setVideoURL(ossVideoURL);
        media.setAudioURL(ossAudioURL);
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
