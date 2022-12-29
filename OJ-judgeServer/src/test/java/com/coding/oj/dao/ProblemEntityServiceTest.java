package com.coding.oj.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coding.oj.pojo.entity.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: sdxu
 * @Description:
 * @Date: 2022/12/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemEntityServiceTest {

    @Autowired
    private ProblemEntityService problemEntityService;

    @Test
    public void Test() {
        System.out.println(problemEntityService.selectProblemById(1000L));
    }

}