package com.example.backend.controller;

import com.example.backend.mapper.BlogPostMapper;
import com.example.backend.model.BlogPost;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PostMapping
    public ResponseEntity<String> createBlog(@RequestBody BlogPost blogPost) {
        if (blogPost.getTitle() == null || blogPost.getContent() == null) {
            return ResponseEntity.badRequest().body("标题和内容不能为空");
        }

        // 自动设置当前时间（如果前端没有传）
        if (blogPost.getCreatedAt() == null || blogPost.getCreatedAt().isEmpty()) {
            blogPost.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        blogPostMapper.insert(blogPost); // 你需要在 mapper 中有 insert 方法
        return ResponseEntity.ok("博客添加成功");
    }
}



