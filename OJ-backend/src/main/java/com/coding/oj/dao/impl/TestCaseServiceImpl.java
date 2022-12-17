package com.coding.oj.dao.impl;

import com.coding.oj.mapper.TestCaseMapper;
import com.coding.oj.pojo.entity.TestCase;
import com.coding.oj.dao.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public List<TestCase> selectAll() {
        return testCaseMapper.selectAll();
    }

    @Override
    public boolean addTestCase(TestCase testCase) {
        int effectedNum = testCaseMapper.insert(testCase);
        return effectedNum > 0;
    }

    @Override
    public List<TestCase> selectByProblem(Long pid) {
        return testCaseMapper.selectByPid(pid);
    }

}
