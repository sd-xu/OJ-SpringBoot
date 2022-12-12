package com.coding.oj.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContestServiceTest {
    @Autowired
    private ContestService contestService;

    @Test
    public void getContestList(){
        List<Map<String,Object>> list = contestService.getContestsByType(1);
        System.out.println(list);
    }
}