package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Star;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Star record);

    Star selectByPrimaryKey(Long id);

    List<Star> selectAll();

    int updateByPrimaryKey(Star record);

    List<Star> selectByAidAndUid(@Param("aid") Long aid, @Param("uid") int uid);

    int deleteByAidAndUid(@Param("aid") Long aid, @Param("uid") Integer uid);
}