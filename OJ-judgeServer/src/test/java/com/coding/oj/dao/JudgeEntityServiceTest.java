package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Judge;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class JudgeEntityServiceTest {
    @Autowired
    private JudgeEntityService judgeEntityService;
    @Test
    void updateById() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void updateValid() {
    }

    @Test
    void getBySubmitId() {
    }

    @Test
    void updateInfo() {
        Judge judge = new Judge();
        judge.setSubmitId(79l);
        judge.setStatus(0);
        judge.setTime(1);
        judge.setMemory(412);
        System.out.println(judgeEntityService.updateInfo(judge));
    }
}