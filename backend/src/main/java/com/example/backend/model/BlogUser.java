package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser {
    private Long id;
    private String username;
    private String password;
    private String role;      // user / admin
    private LocalDateTime createdAt;
}
