package com.coding.oj.controller;

import com.coding.oj.common.CommonResult;
import com.coding.oj.common.Result;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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

    @PostMapping(value = "/judge")
    public Result submitProblemJudge(@RequestBody ToJudgeDTO toJudgeDTO) throws JSONException {

        Judge judge = toJudgeDTO.getJudge();
        if (judge == null || judge.getSubmitId() == null || judge.getUid() == null || judge.getPid() == null) {
            return Result.successResponsemsg("调用参数错误！请检查您的调用参数！");
        }
        judgeService.judge(judge);
        return Result.successResponsemsg("判题机评测完成！");

    }

}
