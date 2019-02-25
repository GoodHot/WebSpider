package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Collect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectRepository extends MongoRepository<Collect, String> {
}
