package com.goodHot.fun.service;

import com.goodHot.fun.domain.Collect;
import org.springframework.data.domain.Page;

public interface CollectService {
    Collect insert(Collect collect);

    Page<Collect> page(Integer pageIndex, Integer pageSize);

    Collect get(String id);
}
