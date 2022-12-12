package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Comment;

import java.util.List;

public interface CommentService {

    Long addComment(Comment comment);

    boolean deleteComment(Long id);

    boolean modifyComment(Comment comment);

    List<Comment> getCommentByAid(Long aid, int pageNum, int pageSize);

    List<Comment> getChildComment(Long parentId);

    Comment getCommentById(Long id);

}
