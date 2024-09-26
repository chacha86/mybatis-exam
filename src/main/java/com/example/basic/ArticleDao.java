package com.example.basic;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    void save(@Param("title") String title, @Param("body") String body);

    // CRUD -> insert, select, update, delete

    @Select("""
            SELECT * FROM article2
            """)
    List<Article> findAll();

    Article findById(Long id);
}
