package com.coding.oj.service;

import com.coding.oj.pojo.entity.Article;
//import javafx.util.Pair;
import jdk.internal.net.http.common.Pair;

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
