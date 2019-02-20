package com.goodHot.fun.service;

import com.goodHot.fun.domain.Story;
import org.springframework.data.domain.Page;

public interface StoryService {
    Story insert(Story story);

    Page<Story> page(Integer pageIndex, Integer pageSize);

    Story get(String id);
}
