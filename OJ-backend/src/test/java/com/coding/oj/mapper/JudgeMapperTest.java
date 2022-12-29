package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Judge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeMapperTest {

    @Autowired
    private JudgeMapper judgeMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectBySubmitId() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void updateBySubmitId() {
    }

    @Test
    public void selectByStatus() {
    }

    @Test
    public void selectByUserIdAndStatus() {
    }

    @Test
    public void selectByUserId() {
    }

    @Test
    public void selectByUserIdAndLanguage() {
    }

    @Test
    public void selectByUserIdAndPid() {
    }

    @Test
    public void getProblemNum() {
    }

    @Test
    public void getLastSubmit() {
    }

    @Test
    public void getUsualLanguage() {
    }

    @Test
    public void getMaxSubmit() {
    }

    @Test
    public void getDifficulty() {
        System.out.println(judgeMapper.getDifficulty(4));
    }

    @Test
    public void lastDateCount() {
    }

    @Test
    public void selectByPidAndStatus() {
    }

    @Test
    public void selectByPidAndLanguage() {
    }

    @Test
    public void selectByAllParam() {
        int userId = 1; Long pid= 1000L; int status = -12; String language= "Java";
        List<Judge> list;
        list = judgeMapper.selectByAllParam(userId, null, null, null, "一");
        System.out.println(list);
    }
}