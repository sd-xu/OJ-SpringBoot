package com.coding.oj.controller;

import com.coding.oj.common.CommonResult;
import com.coding.oj.common.ResultStatus;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.dao.JudgeServerService;
import com.coding.oj.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author: sdxu
 * @Description: 处理代码提交
 * @Date: 2022/12/12
 */
@CrossOrigin
@RestController
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

//    @Value("${oj.judge.token}")
//    private String judgeToken;

    @PostMapping(value = "/judge")
    public CommonResult<Void> submitProblemJudge(@RequestBody ToJudgeDTO toJudgeDTO) {

//        if (!Objects.equals(toJudgeDTO.getToken(), judgeToken)) {
//            return CommonResult.errorResponse("对不起！您使用的判题服务调用凭证不正确！访问受限！", ResultStatus.ACCESS_DENIED);
//        }

        Judge judge = toJudgeDTO.getJudge();

        if (judge == null || judge.getSubmitId() == null || judge.getUid() == null || judge.getPid() == null) {
            return CommonResult.errorResponse("调用参数错误！请检查您的调用参数！");
        }

        judgeService.judge(judge);

        return CommonResult.successResponse("判题机评测完成！");
    }

}
