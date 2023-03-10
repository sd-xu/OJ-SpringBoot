package com.coding.oj.dao.impl;

import com.coding.oj.mapper.CommentMapper;
import com.coding.oj.pojo.entity.Comment;
import com.coding.oj.dao.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long addComment(Comment comment) {
        if(comment.getAid() != null && comment.getUid() != null &&
                comment.getLikeCount() != null && comment.getContent() != null){
            return commentMapper.insert(comment);
        }
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        int effectNum = commentMapper.deleteByPrimaryKey(id);
        return effectNum > 0;
    }

    @Override
    public boolean modifyComment(Comment comment) {
        int effectNum = commentMapper.updateByPrimaryKey(comment);
        return effectNum > 0;
    }

    @Override
    public List<Map<String,Object>> getCommentByAid(Long aid, Integer uid, int pageNum, int pageSize) {
        // 查询功能之前开启分页功能
        List<Map<String,Object>> commentMap = new ArrayList<>();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.selectByAid(aid);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        commentList = pageInfo.getList();

        for (Comment comment : commentList) {
            Map<String,Object> map = new HashMap<>();
            map.put("Comment", comment);
            Long cid = comment.getId();

            map.put("ifLike", !commentMapper.selectCommentLike(cid, uid).isEmpty());

            commentMap.add(map);
        }
        return commentMap;
    }

    @Override
    public List<Comment> getChildComment(Long parentId) {
        return commentMapper.selectChildComment(parentId);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map<?, ?>> getTopThreeComments() {
        return commentMapper.selectTopThreeComments();
    }

}
