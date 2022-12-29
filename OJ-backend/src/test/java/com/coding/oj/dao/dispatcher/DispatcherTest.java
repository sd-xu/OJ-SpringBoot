package com.coding.oj.dao.dispatcher;

import com.coding.oj.pojo.entity.JudgeServer;
import com.coding.oj.dao.JudgeEntityService;

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
    private JudgeEntityService judgeEntityService;

    private JudgeServer server;



    @Test
    public void dispatcherJudge() {
    }



    @Test
    public void reduceCurrentTaskNum() {
    }
}