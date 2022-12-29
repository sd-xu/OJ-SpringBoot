package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Judge;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class JudgeMapperTest {
    @Autowired
    private JudgeMapper judgeMapper;
    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByPrimaryKey() {
        Judge judge = judgeMapper.selectByPrimaryKey(11l);
        judge.setStatus(6);
        System.out.println(judgeMapper.updateByPrimaryKey(judge));
    }

    @Test
    void updateStatusByPrimaryKey() {
    }
}