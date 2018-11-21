package com.goodHot.fun.spider.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class GagPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private AtomicInteger count = new AtomicInteger(0);

    private int size;

    public GagPageProcessor(int size){
        this.size = size;
    }

    @Override
    public void process(Page page) {
        JSONObject json = JSON.parseObject(page.getJson().get());
        JSONArray posts = json.getJSONObject("data").getJSONArray("posts");
        page.putField("posts", posts);
        if(count.addAndGet(posts.size()) >= size){
            return ;
        }
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

}
