package com.goodHot.fun.service.impl;

import com.goodHot.fun.service.DownloadService;
import com.goodHot.fun.util.Download;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.*;

@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private Download download;

    private static final ExecutorService EXECUTE_POOL = Executors.newFixedThreadPool(30);

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
}
