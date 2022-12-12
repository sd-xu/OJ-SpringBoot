package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeveloperServiceTest {
    @Autowired
    private DeveloperService developerService;
    @Test
    public void getDeveloperList() {
        List<Developer> list = developerService.getDeveloperList();
        assertEquals(1,list.size());
    }
}