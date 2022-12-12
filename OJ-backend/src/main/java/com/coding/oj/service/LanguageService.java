package com.coding.oj.service;

import com.coding.oj.pojo.entity.Language;

public interface LanguageService {
    Language getLanguageByPrimaryKey(Long lid);

    String getLanguageBylid(Long lid);
}
