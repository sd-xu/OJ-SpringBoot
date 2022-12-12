package com.coding.oj.service.impl;

import com.coding.oj.mapper.LanguageMapper;
import com.coding.oj.pojo.entity.Language;
import com.coding.oj.service.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageMapper languageMapper;

    @Override
    public Language getLanguageByPrimaryKey(Long lid) {
        return languageMapper.selectByPrimaryKey(lid);
    }

    @Override
    public String getLanguageBylid(Long lid) {
        return languageMapper.selectLanguageByLid(lid);
    }
}
