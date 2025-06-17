package com.example.backend.mapper;

import com.example.backend.model.BlogPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogPostMapper {

    List<BlogPost> findAll();

    BlogPost findById(@Param("id") Long id);

    int insert(BlogPost blogPost);

    int update(BlogPost blogPost);

    int deleteById(@Param("id") Long id);
}
