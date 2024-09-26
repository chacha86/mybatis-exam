package com.example.basic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleDao {


    @Insert("""
                INSERT INTO article2
                				SET  title = #{title}
                					, `body` = #{body}
            """)
    void save(String title, String body);

    // CRUD -> insert, select, update, delete

    @Select("""
            SELECT * FROM article2
            """)
    List<Article> findAll();

    @Select("""
            select * from article2 where id = #{id}
            """)
    Article findById(Long id);
}
