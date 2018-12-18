package com.goodHot.fun.service;

import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;

public interface ProcessService {
    void mp4(MP4Media media);

    void jpeg(JPEGMedia media);

    void coubEmbed(CoubEmbedMedia media);
}
