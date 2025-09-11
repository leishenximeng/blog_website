package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 博客文章实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private LocalDateTime createdAt; // 推荐数据库字段名：created_at
}
