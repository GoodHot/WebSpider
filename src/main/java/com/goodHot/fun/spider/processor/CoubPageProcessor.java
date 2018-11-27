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

public class CoubPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private AtomicInteger count = new AtomicInteger(0);

    private int size;

    public CoubPageProcessor(int size) {
        this.size = size;
    }

    @Override
    public void process(Page page) {
        JSONObject json = JSON.parseObject(page.getJson().get());
        JSONArray coubs = json.getJSONArray("coubs");
        page.putField("posts", coubs);
        if (count.addAndGet(coubs.size()) >= size) {
            return;
        }
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


//    <iframe src="//coub.com/embed/1gvdzr?muted=false&autostart=false&originalSize=false&startWithHD=false" allowfullscreen frameborder="0" width="640" height="360" allow="autoplay"></iframe>


//    public static void main(String[] args) {
//        Spider.create(new CoubPageProcessor())
//                .setDownloader(new JSONDownloader())
//                .setPipelines(Lists.newArrayList(new CoubPipeline()))
//                .addUrl("https://coub.com/api/v2/timeline/hot/weekly?page=1")
//                .run();
//    }
}
