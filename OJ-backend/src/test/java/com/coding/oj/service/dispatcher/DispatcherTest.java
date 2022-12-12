package com.coding.oj.service.dispatcher;

import com.coding.oj.pojo.entity.JudgeServer;
import com.coding.oj.service.JudgeService;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j(topic = "oj")
public class DispatcherTest {
    @Autowired
    private JudgeService judgeService;

    private JudgeServer server;



    @Test
    public void dispatcherJudge() {
    }



    @Test
    public void reduceCurrentTaskNum() {
    }
}