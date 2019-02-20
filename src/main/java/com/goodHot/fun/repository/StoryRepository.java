package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}
