package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Admin;
import com.goodHot.fun.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
}
