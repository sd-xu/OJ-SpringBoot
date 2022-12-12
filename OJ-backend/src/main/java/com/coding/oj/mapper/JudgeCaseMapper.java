package com.coding.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.oj.pojo.entity.JudgeCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JudgeCaseMapper extends BaseMapper<JudgeCase> {

}