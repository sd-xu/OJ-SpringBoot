package com.coding.oj.service;

import com.coding.oj.common.result.CommonResult;
import com.coding.oj.pojo.dto.SubmitJudgeDTO;
import com.coding.oj.pojo.entity.Judge;

import java.util.List;

public interface JudgeService {

    // 提交评测
    CommonResult<Judge> submitProblemJudge(SubmitJudgeDTO judgeDto);

}
