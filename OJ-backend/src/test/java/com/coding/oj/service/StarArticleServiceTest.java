package com.coding.oj.service;


import com.coding.oj.pojo.entity.Star;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarArticleServiceTest {
    @Autowired
    private StarArticleService starArticleService;

    @Test
    public void addStar() {
        Star star = new Star();
        star.setAid(3L);
        star.setUid(1);
        assertEquals(false, starArticleService.addStar(star));
        star.setUid(2);
        assertEquals(true, starArticleService.addStar(star));
    }

    @Test
    public void starOrNot() {
    }

    @Test
    public void dropStar() {
        Star star = new Star();
        star.setAid(3L);
        star.setUid(2);
        assertEquals(true, starArticleService.dropStar(star));
    }
}