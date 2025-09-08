package com.example.backend.mapper;

import com.example.backend.model.BlogPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogPostMapper {

    @Select("SELECT * FROM blog_posts ORDER BY created_at DESC")
    List<BlogPost> findAll();

    @Select("SELECT * FROM blog_posts WHERE id = #{id}")
    BlogPost findById(Long id);

    @Insert("INSERT INTO blog_posts(title, summary, content, created_at) " +
            "VALUES(#{title}, #{summary}, #{content}, #{createdAt})")
    void insert(BlogPost blogPost);

    @Update("UPDATE blog_posts SET title=#{title}, summary=#{summary}, content=#{content} " +
            "WHERE id=#{id}")
    void update(BlogPost blogPost);

    @Delete("DELETE FROM blog_posts WHERE id=#{id}")
    void delete(Long id);
}
