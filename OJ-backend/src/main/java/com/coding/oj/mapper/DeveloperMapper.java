package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Developer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DeveloperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Developer record);

    Developer selectByPrimaryKey(Integer id);

    List<Developer> selectAll();

    int updateByPrimaryKey(Developer record);
}