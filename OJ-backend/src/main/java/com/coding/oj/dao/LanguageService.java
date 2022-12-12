package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Language;

public interface LanguageService {
    Language getLanguageByPrimaryKey(Long lid);

    String getLanguageBylid(Long lid);
}
