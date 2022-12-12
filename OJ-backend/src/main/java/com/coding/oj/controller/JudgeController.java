package com.coding.oj.controller;

import com.coding.oj.common.result.CommonResult;
import com.coding.oj.pojo.dto.SubmitJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.service.JudgeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class JudgeController {
    @Autowired
    private JudgeService judgeService;

    /*
     * @Description 核心方法 判题就此开始
     * 注意返回的是评测过程是否成功完成的结果, 而非判题结果
     */
    @PostMapping (value = "/submit-problem-judge")
    public CommonResult<Judge> submitProblemJudge(@RequestBody SubmitJudgeDTO judgeDto) {
        return judgeService.submitProblemJudge(judgeDto);
    }

    @GetMapping(value = "/getSubmitHistory")
    public Map<String, Object> getSubmitHistory(Integer userId, Integer status, Long lid, Long pid, String title) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Judge> list = judgeService.selectByParam(userId, pid, status, lid, title);
        modelMap.put("JudgeList", list);
        return modelMap;
    }

    @GetMapping(value = "/submitStatusFilter")
    public Map<String, Object> submitStatusFilter(Integer userId, Integer status) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Judge> list = judgeService.selectJudgeByStatus(userId,status);
        modelMap.put("JudgeList", list);
        return modelMap;
    }

    @GetMapping(value = "/languageFilter")
    public Map<String, Object> languageFilter(Integer userId, Long lid) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Judge> list = judgeService.selectJudgeByLanguage(userId, lid);
        modelMap.put("JudgeList", list);
        return modelMap;
    }

    @GetMapping(value = "/problemIdFilter")
    public Map<String, Object> problemIdFilter(Integer userId, Long pid) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Judge> list = judgeService.selectJudgeByProblemId(userId, pid);
        modelMap.put("JudgeList", list);
        return modelMap;
    }

    @GetMapping(value = "/userIdFilter")
    public Map<String, Object> userIdFilter(Integer userId) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Judge> list = judgeService.selectJudgeByUserId(userId);
        modelMap.put("JudgeList", list);
        return modelMap;
    }
}
