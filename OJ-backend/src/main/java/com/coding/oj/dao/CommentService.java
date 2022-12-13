package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    Long addComment(Comment comment);

    boolean deleteComment(Long id);

    boolean modifyComment(Comment comment);

    List<Map<String,Object>> getCommentByAid(Long aid, Integer uid, int pageNum, int pageSize);

    List<Comment> getChildComment(Long parentId);

    Comment getCommentById(Long id);

}
