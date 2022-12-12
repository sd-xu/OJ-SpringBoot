package com.coding.oj.service;

import com.coding.oj.pojo.entity.TestCase;

import java.util.List;

public interface TestCaseService {
    // 获取所有测试用例的信息
    List<TestCase> selectAll();

    // 增加测试用例
    boolean addTestCase(TestCase testCase);

    // 获取某道题目的所有测试用例
    List<TestCase> selectByProblem(Long pid);
}
