package com.example.backend.service.impl;

import com.example.backend.mapper.BlogUserMapper;
import com.example.backend.model.BlogUser;
import com.example.backend.service.BlogUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BlogUserServiceImpl implements BlogUserService {

    private final BlogUserMapper blogUserMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public BlogUserServiceImpl(BlogUserMapper blogUserMapper) {
        this.blogUserMapper = blogUserMapper;
    }

    @Override
    public BlogUser findByUsername(String username) {
        return blogUserMapper.findByUsername(username);
    }

    @Override
    public void register(BlogUser blogUser) {
        // 加密密码
        blogUser.setPassword(passwordEncoder.encode(blogUser.getPassword()));
        blogUserMapper.insert(blogUser);
    }

    @Override
    public boolean checkLogin(String username, String rawPassword) {
        BlogUser user = blogUserMapper.findByUsername(username);
        if (user == null) return false;
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
