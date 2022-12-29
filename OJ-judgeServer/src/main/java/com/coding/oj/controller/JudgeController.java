package com.coding.oj.controller;


import com.coding.oj.common.Result;
import com.coding.oj.pojo.dto.ToJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.service.JudgeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/judge", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject submitProblemJudge(@RequestBody ToJudgeDTO toJudgeDTO) throws Exception {

        Judge judge = toJudgeDTO.getJudge();
        Result result = null;
        JSONObject jsonObject = new JSONObject();
        if (judge == null || judge.getSubmitId() == null || judge.getUid() == null || judge.getPid() == null) {
            result = Result.successResponsemsg("调用参数错误！请检查您的调用参数！");
            jsonObject.put("status", result.getStatus());
            jsonObject.put("data", result.getData());
            jsonObject.put("msg", result.getMsg());
            return jsonObject;
        }
        judgeService.judge(judge);
        result = Result.successResponsemsg("判题机评测完成！");
        jsonObject.put("status", result.getStatus());
        jsonObject.put("data", result.getData());
        jsonObject.put("msg", result.getMsg());
        return jsonObject;
    }

}
