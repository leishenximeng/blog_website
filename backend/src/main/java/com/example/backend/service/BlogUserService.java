package com.example.backend.service;

import com.example.backend.model.BlogUser;

public interface BlogUserService {

    BlogUser findByUsername(String username);

    void register(BlogUser blogUser);

    boolean checkLogin(String username, String password);
}
