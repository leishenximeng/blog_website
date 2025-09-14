package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.model.BlogUser;
import com.example.backend.service.BlogUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class BlogUserController {

    private final BlogUserService blogUserService;

    public BlogUserController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    // 注册
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody BlogUser blogUser) {
        if (blogUser.getUsername() == null || blogUser.getPassword() == null) {
            return ApiResponse.error("用户名和密码不能为空");
        }
        if (blogUserService.findByUsername(blogUser.getUsername()) != null) {
            return ApiResponse.error("用户名已存在");
        }
        blogUserService.register(blogUser);
        return ApiResponse.success("注册成功");
    }

    // 登录
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody BlogUser blogUser, HttpServletRequest request) {
        if (blogUser.getUsername() == null || blogUser.getPassword() == null) {
            return ApiResponse.error("用户名和密码不能为空");
        }
        boolean ok = blogUserService.checkLogin(blogUser.getUsername(), blogUser.getPassword());
        if (!ok) return ApiResponse.error("用户名或密码错误");

        // 登录成功，存 session
        HttpSession session = request.getSession();
        session.setAttribute("user", blogUser.getUsername());
        return ApiResponse.success("登录成功");
    }

    // 退出登录
    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 清除 session
        }
        return ApiResponse.success("退出成功");
    }
}
