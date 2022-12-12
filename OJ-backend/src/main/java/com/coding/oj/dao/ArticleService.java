package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Article;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ArticleService {
    List<Article> getArticleList();

    List<String> getArticleSort();

    List<Pair<String, String>> getArticleSortPercent();

    boolean addArticle(Article article);

    Article getArticleById(Long id);

    boolean deleteArticle(Long id);

    boolean modifyArticle(Article article);

    List<Article> getRecommendation();
}
