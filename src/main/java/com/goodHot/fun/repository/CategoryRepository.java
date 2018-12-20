package com.goodHot.fun.repository;

import com.goodHot.fun.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByIsShow(Boolean isShow);

}
