package com.example.backend.service;

import com.example.backend.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    List<BlogPost> findAll();

    BlogPost findById(Long id);

    void insert(BlogPost blogPost);

    void update(BlogPost blogPost);

    void delete(Long id);
}
