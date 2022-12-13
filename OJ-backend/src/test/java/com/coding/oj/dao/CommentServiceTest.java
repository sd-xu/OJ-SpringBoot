package com.coding.oj.dao;

import com.coding.oj.mapper.CommentMapper;
import com.coding.oj.pojo.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void addComment() {
    }

    @Test
    public void deleteComment() {
    }

    @Test
    public void modifyComment() {
        Comment comment = commentMapper.selectByPrimaryKey(3L); // service没有实现这个功能, 只好用mapper了
        comment.setContent("给xch擦屁股");
        boolean effect = commentService.modifyComment(comment);
        assertEquals(true, effect);
    }

    @Test
    public void getCommentByAid() {

        commentService.getCommentByAid(1l,1,1,3).forEach(System.out::println);
    }

    @Test
    public void getChildComment(){
        List<Comment> list = commentService.getChildComment(1l);
        assertEquals(2,list.size());
    }
}