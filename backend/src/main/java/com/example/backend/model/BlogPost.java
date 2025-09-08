package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    private Long id;
    private String title;
    private String summary;
    private String content;
    private LocalDateTime createdAt; // 改成 LocalDateTime
}
