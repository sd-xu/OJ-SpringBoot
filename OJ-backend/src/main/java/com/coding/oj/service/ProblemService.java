package com.coding.oj.service;

import com.coding.oj.pojo.entity.Problem;

import java.util.List;

public interface ProblemService {
    // 获取所有的题目信息
    List<Problem> selectAll();

    // 增加题目
    boolean addProblem(Problem problem);

    // 通过题目id获取单个题目信息
    Problem selectProblemById(Long id);

    Problem selectProblemByTitle(String title);

    Problem selectProblem(String index);

    List<Problem> getProblems(int pageNum, int pageSize);

}
