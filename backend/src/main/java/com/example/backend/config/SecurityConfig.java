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
                .csrf(csrf -> csrf.disable()) // 关闭 CSRF，便于前端调用
                .authorizeHttpRequests(auth -> auth
                        // 放行注册/登录接口
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                        // 允许博客列表和详情接口匿名访问
                        .requestMatchers("/api/posts/**").permitAll()
                        // 其他接口需要认证
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
