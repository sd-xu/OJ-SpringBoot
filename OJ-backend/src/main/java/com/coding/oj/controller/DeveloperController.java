package com.coding.oj.controller;

import com.coding.oj.pojo.entity.Developer;
import com.coding.oj.dao.DeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;

    @GetMapping(value = "/getDeveloperInfo")
    public Map<String, Object> listDeveloper() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Developer> list = developerService.getDeveloperList();
        modelMap.put("developerList", list);
        return modelMap;
    }
}
