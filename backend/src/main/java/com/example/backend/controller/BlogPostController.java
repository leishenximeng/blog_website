package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.BlogPost;
import com.example.backend.service.BlogPostService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {  // ✅ 统一命名

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    // 查询所有文章
    @GetMapping
    public ApiResponse<List<BlogPost>> getAllPosts() {
        List<BlogPost> posts = blogPostService.findAll();
        return ApiResponse.success(posts);
    }

    // 根据ID查询文章
    @GetMapping("/{id}")
    public ApiResponse<BlogPost> getPostById(@PathVariable Long id) {
        BlogPost post = blogPostService.findById(id);
        if (post == null) {
            return ApiResponse.error("文章不存在");
        }
        return ApiResponse.success(post);
    }

    // 创建文章
    @PostMapping
    public ApiResponse<String> createBlog(@RequestBody BlogPost blogPost) {
        if (blogPost.getTitle() == null || blogPost.getContent() == null) {
            return ApiResponse.error("标题和内容不能为空");
        }

        if (blogPost.getCreatedAt() == null) {
            blogPost.setCreatedAt(LocalDateTime.now());
        }

        blogPostService.insert(blogPost);
        return ApiResponse.success("博客添加成功");
    }

    // 更新文章
    @PutMapping("/{id}")
    public ApiResponse<String> updateBlog(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        BlogPost existing = blogPostService.findById(id);
        if (existing == null) {
            return ApiResponse.error("文章不存在");
        }
        blogPost.setId(id);
        blogPostService.update(blogPost);
        return ApiResponse.success("博客更新成功");
    }

    // 删除文章
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBlog(@PathVariable Long id) {
        BlogPost existing = blogPostService.findById(id);
        if (existing == null) {
            return ApiResponse.error("文章不存在");
        }
        blogPostService.delete(id);
        return ApiResponse.success("博客删除成功");
    }
}
