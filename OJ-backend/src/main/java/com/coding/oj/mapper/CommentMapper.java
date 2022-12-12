package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByAid(Long aid);

    List<Comment> selectChildComment(Long parentId);

    Comment selectNewComment();

}