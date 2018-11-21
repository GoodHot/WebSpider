package com.goodHot.fun.spider.downloader;

import com.github.kevinsawicki.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.PlainText;

public class JSONDownloader extends AbstractDownloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Page download(Request request, Task task) {
        if (task == null || task.getSite() == null) {
            throw new NullPointerException("task or site can not be null");
        }
        HttpRequest req = HttpRequest.get(request.getUrl());
        Page page = new Page();
        try {
            String body = req.body();
            page.setBytes(body.getBytes());
            page.setCharset(req.charset());
            page.setRawText(body);
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
            page.setStatusCode(200);
            page.setDownloadSuccess(true);
            onSuccess(request);
            logger.info("downloading page success {}", request.getUrl());
        } catch (Exception e) {
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
