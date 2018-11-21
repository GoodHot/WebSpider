package com.goodHot.fun.spider.pipeline;

import com.alibaba.fastjson.JSONArray;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CoubPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONArray posts = resultItems.get("posts");
        posts.forEach(post -> {
            System.out.println(post);
        });
    }

}
