package com.goodHot.fun.spider.pipeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.enums.ArchiveEnum;
import com.goodHot.fun.repository.ArchiveRepository;
import com.goodHot.fun.repository.SpiderIndexRepository;
import com.goodHot.fun.util.Encrypts;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
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

    private ArchiveRepository archiveRepository;

    public CoubPipeline(SpiderIndexRepository spiderIndexRepository, ArchiveRepository archiveRepository) {
        super(spiderIndexRepository);
        this.archiveRepository = archiveRepository;
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
                log.warn("[GAG Spider] content is exists ! source: {}, title: {}", archive.getSource(), archive.getTitle());
                continue;
            }
            CoubEmbedMedia media = new CoubEmbedMedia("//coub.com/embed/" + post.getString("permalink"));
            archive.setMedias(Lists.newArrayList(media));
            log.info("[COUB Spider] get content: " + archive);
            archives.add(archive);
        }
        super.saveTree();
        archiveRepository.insert(archives);
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
