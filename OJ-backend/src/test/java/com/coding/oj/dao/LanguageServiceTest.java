package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageServiceTest {
    @Autowired
    private LanguageService languageService;

    @Test
    public void getLanguageByPrimaryKey() {
        Language language = languageService.getLanguageByPrimaryKey(1L);
        System.out.println(language);
    }

    @Test
    public void getLanguageBylid() {
    }
}