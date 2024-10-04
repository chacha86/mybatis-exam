package com.example.basic.article.service;

import com.example.basic.article.entity.Article;
import com.example.basic.article.dao.ArticleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    // 1. 기능 구현
    // 2. 유지 보수를 생각한 코드
    public Article getById(long id) {
        Article article = articleDao.findById(id); // 데이터 처리(비지니스 로직)
        return article;
    }

}
