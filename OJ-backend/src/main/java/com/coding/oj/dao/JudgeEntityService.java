package com.coding.oj.dao;

import com.coding.oj.common.result.CommonResult;
import com.coding.oj.pojo.dto.SubmitJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.pojo.entity.JudgeCase;

import java.util.List;


public interface JudgeEntityService {

    // 通过id更新评测信息
    boolean updateById(Judge judge);

    // 通过提交id更新评测信息
    public boolean updateBySubmitId(Long submitId);

    // 通过提交id获取评测信息
    Judge getBySubmitId(Long submitId);

    // 添加评测信息
    boolean addJudge(Judge judge);

    // 获取所有评测信息
    List<Judge> selectAll();

    // 通过用户id和评测状态获取评测信息表
    List<Judge> selectJudgeByStatus(int userId, int status);

    // 通过用户id获取评测信息表
    List<Judge> selectJudgeByUserId(int userId);

    // 通过用户id和程序语言获取评测信息表
    List<Judge> selectJudgeByLanguage(int userId, String language);

    // 通过用户id和题目id获取评测信息表
    List<Judge> selectJudgeByProblemId(int userId, Long pid);

    List<Judge> selectByParam(int userId, Long pid, Integer status, String language, String title);

    Judge getSubmissionDetail(Long submitId);

    List<JudgeCase> getAllcaseResult(Long submitId);
}
