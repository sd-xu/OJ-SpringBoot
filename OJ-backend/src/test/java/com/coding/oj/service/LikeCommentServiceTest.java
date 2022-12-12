package com.coding.oj.service;

import com.coding.oj.pojo.entity.LikeComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeCommentServiceTest {
    @Autowired
    private LikeCommentService likeCommentService;

    @Test
    public void addLikeComment() {
        LikeComment likeComment = new LikeComment();
        likeComment.setCid(3L);
        likeComment.setUid(3);
        assertEquals(false, likeCommentService.addLikeComment(likeComment));
        likeComment.setUid(2);
        assertEquals(true, likeCommentService.addLikeComment(likeComment));
    }

    @Test
    public void likeCommentOrNot() {
    }

    @Test
    public void dropCommentLike() {
        LikeComment likeComment = new LikeComment();
        likeComment.setCid(3L);
        likeComment.setUid(2);
        assertEquals(true, likeCommentService.dropCommentLike(likeComment));
    }
}