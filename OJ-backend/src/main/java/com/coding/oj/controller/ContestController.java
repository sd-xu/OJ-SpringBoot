package com.coding.oj.controller;

import com.coding.oj.pojo.entity.Contest;
import com.coding.oj.dao.ContestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ContestController {
    @Autowired
    private ContestService contestService;

    @GetMapping (value = "/listContest")
    public Map<String, Object> listContest() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Contest> list = contestService.getContestList();
        modelMap.put("contestList", list);
        return modelMap;
    }

    // 获取站内比赛列表
    @GetMapping (value = "/getOurContests")
    public Map<String, Object> getOurContests(Integer pageNum, Integer pageSize) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Contest> list = contestService.getOurContests(pageNum, pageSize);
        modelMap.put("contestList", list);
        return modelMap;
    }

    // 获取站外比赛列表
    @GetMapping (value = "/getOtherContests")
    public Map<String, Object> getOtherContests(Integer type) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Map<String, Object>> list = contestService.getContestsByType(type);
        modelMap.put("contestList", list);
        return modelMap;
    }

}
