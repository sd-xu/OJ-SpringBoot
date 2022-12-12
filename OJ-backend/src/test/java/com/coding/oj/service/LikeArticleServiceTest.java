package com.coding.oj.service;

import com.coding.oj.pojo.entity.LikeArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeArticleServiceTest {
    @Autowired
    private LikeArticleService likeArticleService;

    @Test
    public void addLikeArticle() {
        LikeArticle likeArticle = new LikeArticle();
        likeArticle.setAid(3L);
        likeArticle.setUid(1);
        assertEquals(false, likeArticleService.addLikeArticle(likeArticle));
        likeArticle.setUid(2);
        assertEquals(true, likeArticleService.addLikeArticle(likeArticle));
    }

    @Test
    public void likeArticleOrNot() {
    }

    @Test
    public void dropArticleLike() {
        LikeArticle likeArticle = new LikeArticle();
        likeArticle.setAid(3L);
        likeArticle.setUid(2);
        assertEquals(true, likeArticleService.dropArticleLike(likeArticle));
    }
}