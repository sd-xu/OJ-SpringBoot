package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProblemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Problem record);

    Problem selectByPrimaryKey(Long id);

    List<Problem> selectAll();

    int updateByPrimaryKey(Problem record);

    Problem selectByTitle(String title);
}