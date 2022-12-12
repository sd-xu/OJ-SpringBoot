package com.coding.oj.service.impl;

import com.coding.oj.mapper.TestCaseMapper;
import com.coding.oj.pojo.entity.TestCase;
import com.coding.oj.service.TestCaseService;
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
        if (effectedNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<TestCase> selectByProblem(Long pid) {
        return testCaseMapper.selectByPid(pid);
    }

}
