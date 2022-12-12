package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    TestCase selectByPrimaryKey(Long id);

    List<TestCase> selectAll();

    int updateByPrimaryKey(TestCase record);

    List<TestCase> selectByPid(Long pid);
}