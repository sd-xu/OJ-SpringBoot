package com.coding.oj.dao.impl;

import com.coding.oj.mapper.ArticleMapper;
import com.coding.oj.pojo.entity.Article;
import com.coding.oj.dao.ArticleService;

import com.coding.oj.pojo.entity.Star;
import org.springframework.data.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> getArticleById(Long id,Integer uid) {
        Map<String,Object> articleMap = new HashMap<>();
        Article article;
        boolean ifLike = false;
        boolean ifSubscribe = false;
        if(uid <0)
        {
            article = articleMapper.selectByPrimaryKey(id);
            articleMap.put("Article",article);
            articleMap.put("ifLike",ifLike);
            articleMap.put("ifSubscribe",ifSubscribe);
        }
        else{
            article = articleMapper.selectArticleLike(id,uid);
            if(article != null){
                ifLike = true;
                articleMap.put("Article",article);
                articleMap.put("ifLike",ifLike);
            }
            article = articleMapper.selectArticleStar(id,uid);
            System.out.println(article);
            if(article != null){
                ifSubscribe = true;
                if(!ifLike)
                    articleMap.put("Article",article);
                articleMap.put("ifSubscribe",ifSubscribe);
            }
            if(!ifLike && !ifSubscribe) {
                article = articleMapper.selectByPrimaryKey(id);
                articleMap.put("Article", article);
                articleMap.put("ifLike", ifLike);
                articleMap.put("ifSubscribe", ifSubscribe);
            }
        }
        return articleMap;
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
