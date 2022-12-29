package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.LikeComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LikeComment record);

    LikeComment selectByPrimaryKey(Long id);

    List<LikeComment> selectAll();

    int updateByPrimaryKey(LikeComment record);

    List<LikeComment> selectByCidAndUid(@Param("cid") Long cid, @Param("uid") int uid);

    int deleteByCidAndUid(@Param("cid") Long cid, @Param("uid") Integer uid);
}