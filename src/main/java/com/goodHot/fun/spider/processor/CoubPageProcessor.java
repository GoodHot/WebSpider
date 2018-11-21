package com.goodHot.fun.spider.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.spider.downloader.JSONDownloader;
import com.goodHot.fun.spider.pipeline.CoubPipeline;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.MalformedURLException;
import java.net.URL;

public class CoubPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        JSONObject json = JSON.parseObject(page.getJson().get());
        page.putField("posts", json.getJSONArray("coubs"));
        Integer nextPage = json.getInteger("page");
        StringBuilder nextURL = new StringBuilder();
        try {
            URL url = new URL(page.getUrl().get());
            nextURL.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath()).append("?page=").append(nextPage + 1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        page.addTargetRequest(nextURL.toString());
        System.out.println("==========================");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CoubPageProcessor())
                .setDownloader(new JSONDownloader())
                .setPipelines(Lists.newArrayList(new CoubPipeline()))
                .addUrl("https://coub.com/api/v2/timeline/hot/weekly?page=1")
                .run();
    }
}
