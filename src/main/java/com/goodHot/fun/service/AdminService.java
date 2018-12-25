package com.goodHot.fun.service;

import com.goodHot.fun.domain.Admin;
import org.springframework.data.domain.Page;

public interface AdminService {
    Page<Admin> page(Integer pageIndex, Integer pageSize);

    Admin insert(Admin admin);
}
