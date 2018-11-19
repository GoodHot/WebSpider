package com.guorer.webspider.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guorer.webspider.downloader.JSONDownloader;
import com.guorer.webspider.pipeline.GagPipeline;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.MalformedURLException;
import java.net.URL;

public class GagPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        JSONObject json = JSON.parseObject(page.getJson().get());
        page.putField("posts", json.getJSONObject("data").getJSONArray("posts"));
        String nextPage = json.getJSONObject("data").getString("nextCursor");
        StringBuilder nextURL = new StringBuilder();
        try {
            URL url = new URL(page.getUrl().get());
            nextURL.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath()).append("?").append(nextPage);
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
//        Spider.create(new GagPageProcessor()).setDownloader(new PageDownloader()).addUrl("https://9gag.com/v1/group-posts/group/default/type/hot").run();
        Spider.create(new GagPageProcessor())
                .setDownloader(new JSONDownloader())
                .setPipelines(Lists.newArrayList(new GagPipeline()))
                .addUrl("https://9gag.com/v1/group-posts/group/default/type/hot")
                .run();
    }
}
