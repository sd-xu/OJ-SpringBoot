package com.coding.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.oj.pojo.entity.Judge;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface JudgeMapper {

    Judge selectByPrimaryKey(@Param("submitId") Long submitId);

    int updateByPrimaryKey(Judge record);

    int updateStatusByPrimaryKey(@Param("status") Integer status, @Param("submitId") Long submitId);

}