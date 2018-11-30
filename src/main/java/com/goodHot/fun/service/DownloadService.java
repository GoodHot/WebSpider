package com.goodHot.fun.service;

import com.goodHot.fun.domain.media.AbstractMedia;

import java.util.List;

public interface DownloadService {

    void downloadForURL(String url);

    void downloadMedias(List<AbstractMedia> medias);
}
