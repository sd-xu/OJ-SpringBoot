package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.JudgeCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JudgeCaseMapper {

    List<JudgeCase> selectTestCaseBySubmitId(Long submitId);

}