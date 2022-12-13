package com.coding.oj.dao;


import com.coding.oj.pojo.entity.JudgeCase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class JudgeCaseEntityServiceTest {
    @Autowired
    private JudgeCaseEntityService judgeCaseEntityService;


    @Test
    void saveBatch() {
        List<JudgeCase> allCaseResList = new LinkedList<>();
        JudgeCase judgeCase = JudgeCase.builder()
                .pid(new Long((long)1000))
                .uid(1)
                .caseId(new Long((long)1000))
                .submitId(new Long((long)1))
                .build();
        allCaseResList.add(judgeCase);
        boolean effect = judgeCaseEntityService.saveBatch(allCaseResList);
        System.out.println(effect);
    }
}