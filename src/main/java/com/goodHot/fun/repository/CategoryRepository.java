package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByIsShow(Boolean isShow);

    Optional<Category> findByEName(String ename);
}
