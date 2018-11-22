package com.goodHot.fun.repository;

import com.goodHot.fun.domain.SpiderIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpiderIndexRepository extends MongoRepository<SpiderIndex, String> {
    SpiderIndex findByName(String name);
}
