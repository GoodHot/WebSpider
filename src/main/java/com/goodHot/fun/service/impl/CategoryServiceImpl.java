package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Category;
import com.goodHot.fun.repository.CategoryRepository;
import com.goodHot.fun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category insert(Category category) {
        return categoryRepository.insert(category);
    }

    @Override
    public Page<Category> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Category> page = categoryRepository.findAll(pageable);
        return page;
    }

    @Override
    public Category findById(String categoryId) {
        Optional<Category> opt = categoryRepository.findById(categoryId);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
