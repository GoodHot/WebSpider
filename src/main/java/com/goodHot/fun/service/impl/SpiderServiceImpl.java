package com.goodHot.fun.service.impl;

import com.goodHot.fun.conf.SpiderConfig;
import com.goodHot.fun.dto.req.SpiderReq;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.repository.SpiderIndexRepository;
import com.goodHot.fun.service.ArchiveService;
import com.goodHot.fun.service.SpiderService;
import com.goodHot.fun.spider.downloader.JSONDownloader;
import com.goodHot.fun.spider.pipeline.CoubPipeline;
import com.goodHot.fun.spider.pipeline.GagPipeline;
import com.goodHot.fun.spider.processor.CoubPageProcessor;
import com.goodHot.fun.spider.processor.GagPageProcessor;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private SpiderConfig config;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private SpiderIndexRepository spiderIndexRepository;

    private static final String GAG_RUNNING_LOCK_KEY = "GAG_SPIDER_RUNNING_LOCK";

    private static final String COUB_RUNNING_LOCK_KEY = "COUB_RUNNING_LOCK_KEY";

    private static final ExecutorService SPIDER_POOL = Executors.newFixedThreadPool(4);

    @Override
    public Boolean startGag(SpiderReq req) {
        String running = redisTemplate.opsForValue().get(GAG_RUNNING_LOCK_KEY);
        ExceptionHelper.param(running != null, "GAG爬虫正在运行");
        SPIDER_POOL.execute(() -> {
            redisTemplate.opsForValue().set(GAG_RUNNING_LOCK_KEY, "running");
            Spider.create(new GagPageProcessor(req.getSize()))
                    .setDownloader(new JSONDownloader())
                    .setPipelines(Lists.newArrayList(new GagPipeline(spiderIndexRepository, archiveService)))
                    .addUrl(config.getGag().getUrl())
                    .thread(1)
                    .run();
            redisTemplate.delete(GAG_RUNNING_LOCK_KEY);

        });
        return Boolean.TRUE;
    }

    @Override
    public Boolean startCoub(SpiderReq req) {
        String running = redisTemplate.opsForValue().get(COUB_RUNNING_LOCK_KEY);
        ExceptionHelper.param(running != null, "COUB爬虫正在运行");
        SPIDER_POOL.execute(() -> {
            redisTemplate.opsForValue().set(COUB_RUNNING_LOCK_KEY, "running");
            Spider.create(new CoubPageProcessor(req.getSize()))
                    .setDownloader(new JSONDownloader())
                    .setPipelines(Lists.newArrayList(new CoubPipeline(spiderIndexRepository, archiveService)))
                    .addUrl(config.getCoub().getUrl())
                    .thread(1)
                    .run();
            redisTemplate.delete(COUB_RUNNING_LOCK_KEY);
        });
        return null;
    }
}
