package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Problem;

public interface ProblemEntityService {

    // 通过题目id获取单个题目信息
    Problem selectProblemById(Long id);

}
