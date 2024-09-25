package com.example.basic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleDao {


    @Insert("""
                INSERT INTO article2
                				SET  title = #{title}
                					, `body` = #{body}
            """)
    void save(String title, String body);
}
