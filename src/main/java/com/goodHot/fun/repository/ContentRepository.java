package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content, String> {
}
