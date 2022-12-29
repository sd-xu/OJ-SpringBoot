package com.coding.oj.dao;


import com.coding.oj.pojo.entity.LikeComment;

public interface LikeCommentService {

    boolean addLikeComment(LikeComment likeComment);

    boolean likeCommentOrNot(Long cid, int uid);

    boolean dropCommentLike(LikeComment likeComment);
}
