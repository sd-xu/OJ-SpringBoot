package com.coding.oj.dao.impl;

import com.coding.oj.dao.TestCaseEntityService;
import com.coding.oj.mapper.TestCaseMapper;
import com.coding.oj.pojo.entity.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseEntityServiceImpl implements TestCaseEntityService {
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Override
    public List<TestCase> selectByProblem(Long pid) {
        return testCaseMapper.selectByPid(pid);
    }

}
