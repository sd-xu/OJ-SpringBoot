package com.coding.oj.service.impl;

import com.coding.oj.mapper.LikeCommentMapper;
import com.coding.oj.pojo.entity.LikeComment;
import com.coding.oj.service.LikeCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeCommentServiceImpl implements LikeCommentService {
    @Autowired
    private LikeCommentMapper likeCommentMapper;

    @Override
    public boolean addLikeComment(LikeComment likeComment) {
        if(likeComment.getCid() == null || likeComment.getUid() == null) {
            return false;
        }
        if (likeCommentOrNot(likeComment.getCid(), likeComment.getUid())) {
            return false; // 已点赞
        }
        int effectNum = likeCommentMapper.insert(likeComment);
        return (effectNum > 0);
    }

    @Override
    public boolean likeCommentOrNot(Long cid, int uid) {
        List<LikeComment> list =  likeCommentMapper.selectByCidAndUid(cid, uid);
        return (!list.isEmpty());
    }

    @Override
    public boolean dropCommentLike(LikeComment likeComment) {
        int effectNum = likeCommentMapper.deleteByCidAndUid(likeComment.getCid(), likeComment.getUid());
        return (effectNum > 0);
    }
}
