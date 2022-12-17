package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Judge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JudgeMapper {

    Judge selectByPrimaryKey(@Param("submitId") Long submitId);

    int updateByPrimaryKey(Judge record);

    int updateStatusByPrimaryKey(@Param("status") Integer status, @Param("submitId") Long submitId);

    int updateInfo(Judge finalJudgeRes);
}