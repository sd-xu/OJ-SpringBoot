package com.coding.oj.controller;

import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.dao.ProblemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    // 获取所有的题目信息
    @GetMapping(value = "/listProblem")
    public Map<String, Object> listProblem(Integer pageNum, Integer pageSize) {
        Map<String, Object> modelMap = new HashMap<>();
        List<Problem> list = problemService.getProblems(pageNum, pageSize);
        // 获取区域列表
        modelMap.put("problemList", list);
        return modelMap;
    }

    // 获取某一个题目的具体信息
    @GetMapping(value = "/getProblem")
    public Map<String, Object> getProblem(String index) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("problem", problemService.selectProblem(index));
        return modelMap;
    }

    // 添加题目
    @PostMapping(value = "/addProblem")
    public Map<String, Object> registerUser(@RequestBody Problem problem)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", problemService.addProblem(problem));
        return modelMap;
    }
}
