package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.PostConfig;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.service.DownloadService;
import com.goodHot.fun.util.Download;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private Download download;

    private static final ExecutorService EXECUTE_POOL = Executors.newFixedThreadPool(40);

    @Autowired
    private PostConfig postConfig;

    private String downloadPath;

    private static final int DOWNLOAT_TIMEOUT = 10;

    @Override
    public void downloadForURL(final String url) {
        Future<Boolean> future = EXECUTE_POOL.submit(() -> {
            try {
                download.downloadFromUrl(url, downloadPath);
            } catch (IOException e) {
                return false;
            }
            return true;
        });

        try {
            Boolean result = future.get(DOWNLOAT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            log.error("[Download]Download Fail: {}", e.getMessage());
        }
    }

    @Override
    public void downloadMedias(List<AbstractMedia> medias) {
        ExceptionHelper.param(CollectionUtils.isEmpty(medias), "未找到下载文件");
        CountDownLatch latch = new CountDownLatch(medias.size());
        medias.forEach(media -> {
            List<String> urls = Lists.newArrayList();
            if (media instanceof MP4Media) {
                MP4Media mp4Media = (MP4Media) media;
                urls.add(mp4Media.getVideoUrl());
            } else if (media instanceof JPEGMedia) {
                JPEGMedia jpegMedia = (JPEGMedia) media;
                urls.add(jpegMedia.getUrl());
            } else if (media instanceof CoubEmbedMedia) {
                CoubEmbedMedia coubEmbedMedia = (CoubEmbedMedia) media;
                urls.add(coubEmbedMedia.getVideoURL());
                urls.add(coubEmbedMedia.getAudioURL());
            }
            EXECUTE_POOL.submit(() -> {
                urls.forEach(url -> {
                    try {
                        download.downloadFromUrl(url, downloadPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                latch.countDown();
            });
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String syncDownloadForURL(String url, String fileName) {
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
