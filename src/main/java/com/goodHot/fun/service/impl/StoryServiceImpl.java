package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Story;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.repository.StoryRepository;
import com.goodHot.fun.service.StoryService;
import com.goodHot.fun.util.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story insert(Story story) {
        // TODO: 2018/12/27 下载文件
        // TODO: 2018/12/27 上传文件到OSS
        // TODO: 2018/12/27 文件加入水印
        // TODO: 2018/12/27 完善文件信息（图片和MP4需要获取width和height） com.goodHot.fun.domain.media.JPEGMedia  com.goodHot.fun.domain.media.MP4Media
        story.setCreated(Times.now());
        story.setModified(Times.now());
        return storyRepository.insert(story);
    }

    @Override
    public Page<Story> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Story> page = storyRepository.findAll(pageable);
        return page;
    }

    @Override
    public Story get(String id) {
        Optional<Story> opt = storyRepository.findById(id);
        ExceptionHelper.param(!opt.isPresent(), "no page [404]!");
        return opt.get();

    }
}
