package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.LikeArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LikeArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LikeArticle record);

    LikeArticle selectByPrimaryKey(Long id);

    List<LikeArticle> selectAll();

    int updateByPrimaryKey(LikeArticle record);

    List<LikeArticle> selectByAidAndUid(@Param("aid") Long aid, @Param("uid") int uid);

    int deleteByAidAndUid(@Param("aid") Long aid, @Param("uid") Integer uid);

}