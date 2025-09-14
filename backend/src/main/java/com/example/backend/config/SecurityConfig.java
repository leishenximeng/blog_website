package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 关闭 CSRF
                .authorizeHttpRequests(auth -> auth
                        // 放行注册、登录、退出接口
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login",
                                "/api/users/logout"
                        ).permitAll()
                        // 博客接口允许匿名访问
                        .requestMatchers("/api/posts/**").permitAll()
                        // 其他接口需要认证
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
