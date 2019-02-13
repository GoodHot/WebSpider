package com.goodHot.fun.service;

import com.goodHot.fun.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> page(Integer pageIndex, Integer pageSize);

    User insertOffical(User user);

    List<User> findByStaff(boolean staff);
}
