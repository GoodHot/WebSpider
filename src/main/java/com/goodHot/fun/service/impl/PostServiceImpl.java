package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.domain.Category;
import com.goodHot.fun.domain.Post;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.CoubEmbedMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.repository.PostRepository;
import com.goodHot.fun.service.CategoryService;
import com.goodHot.fun.service.PostService;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.Times;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProcessService processService;

    @Override
    public Boolean processTask(ArchiveTask task) {
        Archive archive = task.getArchive();
        List<AbstractMedia> medias = archive.getMedias();
        if (CollectionUtils.isNotEmpty(medias)) {
            medias.forEach(media -> mediaProcessFactory(media));
        }
        Category category = categoryService.findById(task.getCategoryId());
        Post post = new Post();
        post.setCreated(Times.now());
        post.setSource(archive.getSource());
        post.setTitle(archive.getTranslateTitle());
        post.setMedias(medias);
        postRepository.save(post);
        return StringUtils.isNotBlank(post.getId());
    }

    private void mediaProcessFactory(AbstractMedia media) {
        if (media instanceof MP4Media) {
            processService.mp4((MP4Media) media);
        } else if (media instanceof JPEGMedia) {
            processService.jpeg((JPEGMedia) media);
        } else if (media instanceof CoubEmbedMedia) {
            processService.coubEmbed((CoubEmbedMedia) media);
        }
    }

}
