package com.coding.oj.service;

import com.coding.oj.pojo.entity.LikeArticle;

public interface LikeArticleService {
    boolean addLikeArticle(LikeArticle likeArticle);

    boolean likeArticleOrNot(Long aid, int uid);

    boolean dropArticleLike(LikeArticle likeArticle);

}
