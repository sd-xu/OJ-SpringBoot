package com.coding.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.oj.pojo.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {

}