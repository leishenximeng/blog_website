package com.example.backend.controller;

import com.example.backend.mapper.BlogPostMapper;
import com.example.backend.model.BlogPost;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogController {

    private final BlogPostMapper blogPostMapper;

    public BlogController(BlogPostMapper blogPostMapper) {
        this.blogPostMapper = blogPostMapper;
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostMapper.findAll();
    }

    @GetMapping("/{id}")
    public BlogPost getPostById(@PathVariable Long id) {
        return blogPostMapper.findById(id);
    }
}
