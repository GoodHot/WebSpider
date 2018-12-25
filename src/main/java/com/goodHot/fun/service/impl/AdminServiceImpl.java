package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Admin;
import com.goodHot.fun.repository.AdminRepository;
import com.goodHot.fun.service.AdminService;
import com.goodHot.fun.util.Encrypts;
import com.goodHot.fun.util.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Page<Admin> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Admin> page = adminRepository.findAll(pageable);
        return page;
    }

    @Override
    public Admin insert(Admin admin) {
        admin.setPassword(Encrypts.passwd(admin.getPassword()));
        admin.setCreated(Times.now());
        admin.setModified(Times.now());
        return adminRepository.insert(admin);
    }
}
