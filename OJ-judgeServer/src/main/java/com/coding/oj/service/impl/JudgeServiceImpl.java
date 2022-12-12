package com.coding.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.coding.oj.judge.JudgeContext;
import com.coding.oj.pojo.dto.TestJudgeReq;
import com.coding.oj.pojo.dto.TestJudgeRes;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.service.JudgeService;
import com.coding.oj.service.ProblemService;
import com.coding.oj.utils.Constants;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Objects;

/**
 * @Description:
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${oj-judge-server.name}")
    private String name;

    @Resource
    private JudgeService judgeService;

    @Resource
    private ProblemService problemService;

    @Resource
    private JudgeContext judgeContext;

    @Override
    public void judge(Judge judge) {
        // 标志该判题过程进入编译阶段
        // 写入当前判题服务的名字
        UpdateWrapper<Judge> judgeUpdateWrapper = new UpdateWrapper<>();
        judgeUpdateWrapper.set("status", Constants.Judge.STATUS_COMPILING.getStatus())
                .set("judger", name)
                .eq("submit_id", judge.getSubmitId())
                .ne("status", Constants.Judge.STATUS_CANCELLED.getStatus());
        boolean isUpdatedOk = judgeService.update(judgeUpdateWrapper);

        // 没更新成功，则可能表示该评测被取消 或者 judge记录被删除了，则结束评测
        if (!isUpdatedOk){
            judgeContext.updateOtherTable(judge.getSubmitId(),
                    Constants.Judge.STATUS_CANCELLED.getStatus(),
                    judge.getCid(),
                    judge.getUid(),
                    judge.getPid(),
                    judge.getGid(),
                    null,
                    null);
            return;
        }

        // 进行判题操作
        QueryWrapper<Problem> problemQueryWrapper = new QueryWrapper<>();
        problemQueryWrapper.select("id",
                        "type",
                        "io_score",
                        "difficulty",
                        "judge_mode",
                        "judge_case_mode",
                        "time_limit",
                        "memory_limit",
                        "stack_limit",
                        "user_extra_file",
                        "judge_extra_file",
                        "case_version",
                        "spj_code",
                        "spj_language",
                        "problem_id",
                        "is_remove_end_blank")
                .eq("id", judge.getPid());
        Problem problem = problemService.getOne(problemQueryWrapper);
        Judge finalJudgeRes = judgeContext.Judge(problem, judge);

        // 更新该次提交
        judgeService.updateById(finalJudgeRes);

        if (!Objects.equals(finalJudgeRes.getStatus(), Constants.Judge.STATUS_SUBMITTED_FAILED.getStatus())) {
            // 更新其它表
            judgeContext.updateOtherTable(finalJudgeRes.getSubmitId(),
                    finalJudgeRes.getStatus(),
                    judge.getCid(),
                    judge.getUid(),
                    judge.getPid(),
                    judge.getGid(),
                    finalJudgeRes.getScore(),
                    finalJudgeRes.getTime());
        }
    }

    @Override
    public TestJudgeRes testJudge(TestJudgeReq testJudgeReq) {
        return judgeContext.testJudge(testJudgeReq);
    }

}