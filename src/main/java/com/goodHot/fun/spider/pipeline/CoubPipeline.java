package com.goodHot.fun.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.enums.ArchiveEnum;
import com.goodHot.fun.repository.SpiderIndexRepository;
import com.goodHot.fun.service.ArchiveService;
import com.goodHot.fun.util.Emitters;
import com.goodHot.fun.util.Encrypts;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
public class CoubPipeline extends BasePipeline {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private ArchiveService archiveService;

    public CoubPipeline(SpiderIndexRepository spiderIndexRepository, ArchiveService archiveService, ResponseBodyEmitter emitter) {
        super(spiderIndexRepository);
        super.emitter = emitter;
        this.archiveService = archiveService;
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        JSONArray posts = resultItems.get("posts");
        List<Archive> archives = Lists.newArrayList();
        for (int i = 0; i < posts.size(); i++) {
            JSONObject post = posts.getJSONObject(i);
            Archive archive = new Archive();
            archive.setSource("https://coub.com/view/" + post.getString("permalink"));
            archive.setCreated(formatDate(post.getString("created_at")));
            archive.setTitle(post.getString("title"));
            archive.setTranscoding(true);
            archive.setUnique(Encrypts.md5(archive.getSource()));
            archive.setStatus(ArchiveEnum.Status.WAIT.status);
            if (super.isExists(archive.getUnique())) {
                log.warn("[COUB Spider] content is exists ! source: {}, title: {}", archive.getSource(), archive.getTitle());
                Emitters.send(super.emitter, "[COUB Spider] repeat:" + archive.getSource());
                continue;
            }
            JSONObject source = post.getJSONObject("file_versions").getJSONObject("html5");
            String videoURL = source.getJSONObject("video").getJSONObject("med").getString("url");
            String audioURL = source.getJSONObject("audio").getJSONObject("med").getString("url");
            CoubEmbedMedia media = new CoubEmbedMedia("//coub.com/embed/" + post.getString("permalink"));
            media.setVideoURL(videoURL);
            media.setAudioURL(audioURL);
            archive.setMedias(Lists.newArrayList(media));
            log.info("[COUB Spider] get content: " + archive);
            Emitters.send(super.emitter, "[COUB Spider] get content: " + JSON.toJSONString(archive));
            archives.add(archive);
        }
        super.saveTree();
        archiveService.insertForTranslate(archives);
    }

    private Date formatDate(String dateStr) {
        Date parse = null;
        try {
            parse = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    @Override
    public String getSpiderName() {
        return "coub";
    }
}
