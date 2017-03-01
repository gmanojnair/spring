package com.manoj.spring.auth.service;

import com.manoj.spring.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
