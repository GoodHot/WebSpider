package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.domain.Category;
import com.goodHot.fun.domain.Post;
import com.goodHot.fun.domain.media.*;
import com.goodHot.fun.repository.PostRepository;
import com.goodHot.fun.service.CategoryService;
import com.goodHot.fun.service.PostService;
import com.goodHot.fun.service.ProcessService;
import com.goodHot.fun.util.Times;
import com.goodHot.fun.util.upyun.com.upyun.UpException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;

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
        LinkedList<AbstractMedia> medias = new LinkedList<>(archive.getMedias());
        if (StringUtils.isNotBlank(archive.getTranslateTitle())) {
            medias.addFirst(new TextMedia(archive.getTranslateTitle()));
        } else {
            medias.addFirst(new TextMedia(archive.getTitle()));
        }
        if (CollectionUtils.isNotEmpty(medias)) {
            medias.forEach(media -> {
                try {
                    mediaProcessFactory(media);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UpException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Category category = categoryService.findById(task.getCategoryId());
        Post post = new Post();
        post.setCreated(Times.now());
        post.setSource(archive.getSource());
        post.setTitle(archive.getTranslateTitle());
        post.setMedias(medias);
        post.setCategory(category);
        postRepository.save(post);
        return StringUtils.isNotBlank(post.getId());
    }

    @Override
    public Page<Post> page(String category, int index, int size) {
        Pageable pageable = new PageRequest(index - 1, size, new Sort(Sort.Direction.DESC, "created"));
        Page<Post> page = postRepository.findAll(pageable);
        return page;
    }

    private void mediaProcessFactory(AbstractMedia media) throws IOException, UpException, InterruptedException {
        if (media instanceof MP4Media) {
            processService.mp4((MP4Media) media);
        } else if (media instanceof JPEGMedia) {
            processService.jpeg((JPEGMedia) media);
        } else if (media instanceof CoubEmbedMedia) {
            processService.coubEmbed((CoubEmbedMedia) media);
        } else if (media instanceof TextMedia) {
            // skip
        }
    }

}
