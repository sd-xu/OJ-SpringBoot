package com.coding.oj.controller;

import com.coding.oj.pojo.entity.Status;
import com.coding.oj.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping(value = "/getJudgeInfo")
    public Map<String, Object> listStatus() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Status> list = statusService.getStatusList();
        modelMap.put("statusList", list);
        return modelMap;
    }
}
