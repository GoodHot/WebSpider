package com.goodHot.fun.repository;

import com.goodHot.fun.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository  extends MongoRepository<User, String> {
    List<User> findByStaff(boolean staff);
}
