package com.example.backend.service.impl;

import com.example.backend.mapper.BlogPostMapper;
import com.example.backend.model.BlogPost;
import com.example.backend.service.BlogPostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostMapper blogPostMapper;

    public BlogPostServiceImpl(BlogPostMapper blogPostMapper) {
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    public List<BlogPost> findAll() {
        return blogPostMapper.findAll();
    }

    @Override
    public BlogPost findById(Long id) {
        return blogPostMapper.findById(id);
    }

    @Override
    public void insert(BlogPost blogPost) {
        blogPostMapper.insert(blogPost);
    }

    @Override
    public void update(BlogPost blogPost) {
        blogPostMapper.update(blogPost);
    }

    @Override
    public void delete(Long id) {
        blogPostMapper.delete(id);
    }
}
