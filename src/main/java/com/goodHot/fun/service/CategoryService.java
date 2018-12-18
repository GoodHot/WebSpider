package com.goodHot.fun.service;

import com.goodHot.fun.domain.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Category insert(Category category);

    Page<Category> page(Integer pageIndex, Integer pageSize);

    Category findById(String categoryId);
}
