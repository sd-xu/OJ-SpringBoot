package com.coding.oj.service.impl;

import com.coding.oj.mapper.CommentMapper;
import com.coding.oj.pojo.entity.Comment;
import com.coding.oj.service.CommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Long addComment(Comment comment) {
        if(comment.getAid() != null && comment.getUid() != null &&
                comment.getLikeCount() != null && comment.getContent() != null){
            int effectNum = commentMapper.insert(comment);
            if(effectNum > 0) return commentMapper.selectNewComment().getId();
            else return null;
        }
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        int effectNum = commentMapper.deleteByPrimaryKey(id);
        if(effectNum > 0) return true;
        else return false;
    }

    @Override
    public boolean modifyComment(Comment comment) {
        int effectNum = commentMapper.updateByPrimaryKey(comment);
        if(effectNum>0) return true;
        else return  false;
    }

    @Override
    public List<Comment> getCommentByAid(Long aid, int pageNum, int pageSize) {
        // 查询功能之前开启分页功能
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectByAid(aid);
        // 查询功能之后可以获取分页相关的所有数据
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Comment> getChildComment(Long parentId) {
        return commentMapper.selectChildComment(parentId);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

}
