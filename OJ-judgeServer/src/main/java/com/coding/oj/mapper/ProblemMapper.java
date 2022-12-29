package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProblemMapper {

    Problem selectByPrimaryKey(Long id);

}