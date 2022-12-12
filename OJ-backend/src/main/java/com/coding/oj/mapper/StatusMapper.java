package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Status;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Status record);

    Status selectByPrimaryKey(Integer id);

    List<Status> selectAll();

    int updateByPrimaryKey(Status record);
}