package com.goodHot.fun.spider.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.repository.ArchiveRepository;
import com.goodHot.fun.util.Times;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class GagPipeline implements Pipeline {

    public GagPipeline(ArchiveRepository archiveRepository){
        this.archiveRepository = archiveRepository;
    }

    private ArchiveRepository archiveRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONArray posts = resultItems.get("posts");
        List<Archive> archives = Lists.newArrayList();
        for (int i = 0; i < posts.size(); i++) {
            JSONObject post = posts.getJSONObject(i);
            Archive archive = new Archive();
            archive.setSource(post.getString("url"));
            archive.setCreated(Times.timeForMS(post.getLongValue("creationTs")));
            archive.setTitle(post.getString("title"));
            archive.setTranscoding(false);
            JSONObject image = post.getJSONObject("images").getJSONObject("image700");
            if (image == null) {
                image = post.getJSONObject("images").getJSONObject("image460");
            }
            JPEGMedia poster = new JPEGMedia(image.getString("url"), image.getInteger("width"), image.getInteger("height"));
            archive.setPoster(poster.getUrl());

            JSONObject video = post.getJSONObject("images").getJSONObject("image460sv");
            MP4Media mp4 = null;
            if (video != null) {
                mp4 = new MP4Media(video.getString("url"), poster.getUrl(), video.getInteger("width"), video.getInteger("height"), (video.getInteger("hasAudio") == null) ? false : true);
            }

            List<AbstractMedia> medias = Lists.newArrayList();
            if (mp4 == null) {
                medias.add(poster);
            } else {
                medias.add(mp4);
            }
            archive.setMedias(medias);
            archives.add(archive);
        }
        archiveRepository.insert(archives);
    }
}
