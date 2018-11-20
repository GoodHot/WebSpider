package com.goodHot.fun.webspider.downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.PlainText;

import java.io.IOException;

public class PageDownloader extends AbstractDownloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Page download(Request request, Task task) {
        if (task == null || task.getSite() == null) {
            throw new NullPointerException("task or site can not be null");
        }
        Page page = new Page();
        try {
            Document doc = Jsoup.connect(request.getUrl()).get();
            page.setBytes(doc.html().getBytes());
            page.setCharset(doc.charset().name());
            page.setRawText(doc.html());
            page.setUrl(new PlainText(request.getUrl()));page.setRequest(request);
            page.setStatusCode(200);
            page.setDownloadSuccess(true);
            onSuccess(request);
            logger.info("downloading page success {}", request.getUrl());
        } catch (IOException e) {
            logger.warn("download page {} error", request.getUrl(), e);
            onError(request);
            return Page.fail();
        }
        return page;
    }

    @Override
    public void setThread(int threadNum) {

    }
}
