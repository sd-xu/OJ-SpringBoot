package com.coding.oj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeServerServiceTest {
    @Autowired
    private  JudgeServerService judgeServerService;
    @Test
    public void updateTaskNum() {
        //int taskNumber_1 = judgeServerService.selectById(1).getTaskNumber();
        boolean effect_num = judgeServerService.updateTaskNum(1);
        //int taskNumber_2 = judgeServerService.selectById(1).getTaskNumber();
        assertEquals(effect_num,true);
        //assertEquals(taskNumber_1-1,taskNumber_2);
    }
}