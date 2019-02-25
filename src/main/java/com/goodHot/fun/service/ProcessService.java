package com.goodHot.fun.service;

import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.util.upyun.com.upyun.UpException;

import java.io.IOException;

public interface ProcessService {
    void mp4(MP4Media media) throws IOException, UpException;

    void jpeg(JPEGMedia media) throws IOException, UpException;

    void coubEmbed(CoubEmbedMedia media) throws IOException, UpException, InterruptedException;
}
