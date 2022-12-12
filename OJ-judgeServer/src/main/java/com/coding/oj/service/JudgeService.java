package com.coding.oj.service;

import com.coding.oj.common.exception.SystemError;
import com.coding.oj.pojo.dto.TestJudgeReq;
import com.coding.oj.pojo.dto.TestJudgeRes;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;

import java.util.HashMap;

public interface JudgeService {

    public void judge(Judge judge);

    public TestJudgeRes testJudge(TestJudgeReq testJudgeReq);

}
