package com.coding.oj.controller;

import com.coding.oj.pojo.entity.Language;
import com.coding.oj.service.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping(value = "/getCompilerInfo")
    public Map<String, Object> listLanguage(Long lid) {
        Map<String, Object> modelMap = new HashMap<>();
        Language language = languageService.getLanguageByPrimaryKey(lid);
        modelMap.put("language", language);
        return modelMap;
    }
}
