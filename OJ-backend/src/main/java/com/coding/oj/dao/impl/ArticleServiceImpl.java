package com.coding.oj.dao.impl;

import com.coding.oj.mapper.ArticleMapper;
import com.coding.oj.pojo.entity.Article;
import com.coding.oj.dao.ArticleService;

import org.springframework.data.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Map<String,Object>> getArticleList(Integer userId) {
        List<Map<String,Object>> articleMap = new ArrayList<>();
        List<Article> articleList = articleMapper.selectAll();
        for(Article article : articleList){
            Map<String,Object> map = new HashMap<>();
            map.put("Article", article);
            Long aid = article.getId();
            map.put("ifLike", articleMapper.selectArticleLike(aid,userId) != null);
            articleMap.add(map);
        }
        return articleMap;
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
        int num = Math.min(list.size(), 5);
        return list.subList(0, num);
    }

    @Override
    public boolean addArticle(Article article) {
        if(article.getUid() != null && article.getTitle() != null && article.getContent() != null
        && article.getLikeCount() != null && article.getCommentCount() != null && article.getStarCount() != null) {
            int effectNum = articleMapper.insert(article);
            return effectNum > 0;
        }
        return false;
    }

    @Override
    public Map<String, Object> getArticleById(Long id, Integer uid) {
        Map<String,Object> articleMap = new HashMap<>();
        Article article = articleMapper.selectByPrimaryKey(id);
        articleMap.put("Article", article);
        if(uid < 0) { // 游客
            articleMap.put("ifLike", false);
            articleMap.put("ifSubscribe", false);
        }
        else {
            articleMap.put("ifLike", articleMapper.selectArticleLike(id, uid) != null);      // 有点赞
            articleMap.put("ifSubscribe", articleMapper.selectArticleStar(id, uid) != null); // 有收藏
        }
        return articleMap;
    }

    @Override
    public boolean deleteArticle(Long id) {
        int effectNum = articleMapper.deleteByPrimaryKey(id);
        return effectNum > 0;
    }

    @Override
    public boolean modifyArticle(Article article) {
        int effectNum = articleMapper.updateByPrimaryKey(article);
        return effectNum > 0;
    }
}
