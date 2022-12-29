package com.coding.oj.dao.impl;


import com.coding.oj.dao.ProblemEntityService;
import com.coding.oj.mapper.ProblemMapper;
import com.coding.oj.pojo.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemEntityServiceImpl implements ProblemEntityService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Problem selectProblemById(Long id) {
        return problemMapper.selectByPrimaryKey(id);
    }

}
