package com.coding.oj.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContestMapperTest {
    @Autowired
    private ContestMapper contestMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(contestMapper.selectByPrimaryKey(1000L, 1));
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {

    }

    @Test
    public void getContestNum() {
    }

    @Test
    public void selectByType() {
        System.out.println(contestMapper.selectByType(0));
    }
}