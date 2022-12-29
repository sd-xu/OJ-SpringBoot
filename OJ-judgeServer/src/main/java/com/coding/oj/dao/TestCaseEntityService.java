package com.coding.oj.dao;

import com.coding.oj.pojo.entity.TestCase;

import java.util.List;

public interface TestCaseEntityService {

    // 获取某道题目的所有测试用例
    List<TestCase> selectByProblem(Long pid);

}
