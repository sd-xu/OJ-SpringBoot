package com.coding.oj.service.impl;

import com.coding.oj.mapper.LikeArticleMapper;
import com.coding.oj.pojo.entity.LikeArticle;
import com.coding.oj.service.LikeArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeArticleServiceImpl implements LikeArticleService {
    @Autowired
    private LikeArticleMapper likeArticleMapper;

    @Override
    public boolean addLikeArticle(LikeArticle likeArticle) {
        if(likeArticle.getAid() == null || likeArticle.getUid() == null) {
            return false;
        }
        if (likeArticleOrNot(likeArticle.getAid(), likeArticle.getUid())) {
            return false; // 已点赞
        }
        int effectNum = likeArticleMapper.insert(likeArticle);
        return (effectNum > 0);
    }

    @Override
    public boolean likeArticleOrNot(Long aid, int uid) {
        List<LikeArticle> list =  likeArticleMapper.selectByAidAndUid(aid, uid);
        return (!list.isEmpty());
    }

    @Override
    public boolean dropArticleLike(LikeArticle likeArticle) {
        int effectNum = likeArticleMapper.deleteByAidAndUid(likeArticle.getAid(), likeArticle.getUid());
        return (effectNum > 0);
    }

}
