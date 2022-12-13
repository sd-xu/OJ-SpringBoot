package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestCaseMapper {

    List<TestCase> selectByPid(Long pid);

}