package com.coding.oj.controller;

import com.coding.oj.pojo.entity.TestCase;
import com.coding.oj.dao.TestCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    //获取所有测试用例
    @GetMapping(value = "/listTestCase")
    public Map<String, Object> listProblem() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<TestCase> list = testCaseService.selectAll();
        modelMap.put("problemList", list);
        return modelMap;
    }

    //获取某个题目的所有测试用例
    @GetMapping(value = "/listProblemTestCase")
    public Map<String, Object> listProblemTestCase(Long pid) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<TestCase> list = testCaseService.selectByProblem(pid);
        modelMap.put("problemList", list);
        return modelMap;
    }

    //增加测试用例
    @PostMapping(value = "/addTestCase")
    public Map<String, Object> registerUser(@RequestBody TestCase testCase)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", testCaseService.addTestCase(testCase));
        return modelMap;
    }
}
