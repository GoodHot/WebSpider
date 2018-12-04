package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Post, String> {
}
