package com.goodHot.fun.spider.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.goodHot.fun.repository.SpiderIndexRepository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

public class CoubPipeline extends BasePipeline {

    public CoubPipeline(SpiderIndexRepository spiderIndexRepository) {
        super(spiderIndexRepository);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONArray posts = resultItems.get("posts");
        posts.forEach(post -> {
            System.out.println(post);
        });
    }

    @Override
    public String getSpiderName() {
        return "coub";
    }
}
