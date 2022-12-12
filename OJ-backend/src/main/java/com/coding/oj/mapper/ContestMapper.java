package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Contest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContestMapper {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("uid") Integer uid);

    int insert(Contest record);

    Contest selectByPrimaryKey(@Param("id") Long id, @Param("uid") Integer uid);

    List<Contest> selectAll();

    int updateByPrimaryKey(Contest record);

    int getContestNum(int userId);

    List<Contest> selectByType(int type);

}