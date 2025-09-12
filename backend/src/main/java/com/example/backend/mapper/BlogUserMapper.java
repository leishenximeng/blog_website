package com.example.backend.mapper;

import com.example.backend.model.BlogUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BlogUserMapper {

    @Select("SELECT * FROM blog_user WHERE username = #{username}")
    BlogUser findByUsername(String username);

    @Insert("INSERT INTO blog_user(username, password, created_at) VALUES(#{username}, #{password}, NOW())")
    void insert(BlogUser blogUser);
}
