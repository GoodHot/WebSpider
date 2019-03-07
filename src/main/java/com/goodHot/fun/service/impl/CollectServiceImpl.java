package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.UpYunConfig;
import com.goodHot.fun.domain.Collect;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.enums.MediaEnum;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.repository.CollectRepository;
import com.goodHot.fun.service.CollectService;
import com.goodHot.fun.service.DownloadService;
import com.goodHot.fun.util.Encrypts;
import com.goodHot.fun.util.Times;
import com.goodHot.fun.util.UpYunUtil;
import com.upyun.UpException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private UpYunUtil upYunUtil;

    @Autowired
    private UpYunConfig upYunConfig;

    @Override
    public Collect insert(Collect collect) {
        List<AbstractMedia> medias = collect.getMedias();
        medias.forEach(media -> {
            if (media instanceof MP4Media && StringUtils.isNotBlank(media.getSource())) {
                MP4Media mp4 = ((MP4Media) media);
                String fileName = Encrypts.md5(mp4.getVideoUrl()) + MediaEnum.VIDEO.suffix;
                String path = downloadService.syncDownloadForURL(mp4.getVideoUrl(), fileName);
                try {
                    String videoURL = upYunUtil.upload(path, upYunConfig.getBucket().collectPath(fileName));
                    mp4.setVideoUrl(videoURL);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UpException e) {
                    e.printStackTrace();
                }
                // TODO: 2019/3/7 获取封面图片
                // TODO: 2019/3/7 上传OSS
                // TODO: 2019/3/7 FFMPEG 获取到封面图片，并且设置 mp4.setPosterUrl(poster);
            }
        });
        collect.setCreated(Times.now());
        collect.setModified(Times.now());
        return collectRepository.insert(collect);
    }

    @Override
    public Page<Collect> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Collect> page = collectRepository.findAll(pageable);
        return page;
    }

    @Override
    public Collect get(String id) {
        Optional<Collect> opt = collectRepository.findById(id);
        ExceptionHelper.param(!opt.isPresent(), "no page [404]!");
        return opt.get();

    }
}
