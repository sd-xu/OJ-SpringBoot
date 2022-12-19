package com.coding.oj.dao;

import com.coding.oj.pojo.entity.JudgeCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j(topic = "oj")
public class JudgeCaseServiceTest {

    @Autowired
    private JudgeCaseService judgeCaseService;


    @Test
    public void saveBatch(){

    }

}