package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    private Long id;
    private String title;
    private String content;
    private String createdAt;

    // 如果你需要自定义构造函数，也可以自己写，否则Lombok会生成无参构造和全参构造
}
