package com.coding.oj.service.impl;

import com.coding.oj.mapper.ArticleMapper;
import com.coding.oj.pojo.entity.Article;
import com.coding.oj.service.ArticleService;

import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getArticleList() {
        return articleMapper.selectAll();
    }

    @Override
    public List<String> getArticleSort() {
        return articleMapper.selectSort();
    }

    @Override
    public List<Pair<String, String>> getArticleSortPercent() {
        return articleMapper.selectSortPercent();
    }

    @Override
    public List<Article> getRecommendation() {
        List<Article> list = articleMapper.selectRecommendation();
        int num = list.size() >= 5 ? 5 : list.size();
        return list.subList(0, num);
    }

    @Override
    public boolean addArticle(Article article) {
        if(article.getUid() != null && article.getTitle() != null && article.getContent() != null
        && article.getLikeCount() != null && article.getCommentCount() != null && article.getStarCount() != null) {
            int effectNum = articleMapper.insert(article);
            if(effectNum > 0) return true;
            else return false;
        }
        return false;
    }

    @Override
    public Article getArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteArticle(Long id) {
        int effectNum = articleMapper.deleteByPrimaryKey(id);
        if(effectNum > 0) return true;
        else return  false;
    }

    @Override
    public boolean modifyArticle(Article article) {
        int effectNum = articleMapper.updateByPrimaryKey(article);
        if(effectNum > 0) return true;
        else return  false;
    }
}
