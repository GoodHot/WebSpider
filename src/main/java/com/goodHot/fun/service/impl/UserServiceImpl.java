package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.User;
import com.goodHot.fun.enums.SocialEnum;
import com.goodHot.fun.repository.UserRepository;
import com.goodHot.fun.service.UserService;
import com.goodHot.fun.util.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        return userRepository.findAll(pageable);
    }

    @Override
    public User insertOffical(User user) {
        user.setChannel(SocialEnum.Channel.OFFICIAL);
        user.setCreated(Times.now());
        user.setModified(Times.now());
        return userRepository.insert(user);
    }

    @Override
    public List<User> findByStaff(boolean staff) {
        return userRepository.findByStaff(staff);
    }
}
