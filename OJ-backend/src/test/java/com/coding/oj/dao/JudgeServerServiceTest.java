package com.coding.oj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeServerServiceTest {
    @Autowired
    private  JudgeServerService judgeServerService;
    @Test
    public void updateTaskNum() {
        boolean effect_num = judgeServerService.release(1);
        assertTrue(effect_num);
    }
}