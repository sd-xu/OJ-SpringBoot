package com.coding.oj.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void deleteByPrimaryKey() {
        articleMapper.deleteByPrimaryKey(1L);
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        System.out.println(articleMapper.selectAll());
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectSort() {
    }

    @Test
    public void selectSortPercent() {
    }
}