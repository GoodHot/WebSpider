package com.goodHot.fun.service;

import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.domain.Post;
import org.springframework.data.domain.Page;

public interface PostService {
    Boolean processTask(ArchiveTask task);

    Page<Post> page(String category, int index, int size);
}
