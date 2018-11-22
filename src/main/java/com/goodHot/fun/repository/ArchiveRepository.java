package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Archive;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchiveRepository extends MongoRepository<Archive, String> {
}
