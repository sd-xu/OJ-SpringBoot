package com.coding.oj.service;

import com.coding.oj.pojo.entity.Judge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JudgeServiceTest {
    @Autowired
    private JudgeService judgeService;

    @Test
    public void updateById() {

        Judge judge = judgeService.getBySubmitId(new Long((long)3));
        judge.setStatus(200);
        judgeService.updateById(judge);
        Judge judge1 = judgeService.getBySubmitId(new Long((long)3));
        assertEquals(200,judge1.getStatus());
    }

    @Test
    public void getBySubmitId() {
        Judge judge = judgeService.getBySubmitId(new Long((long)3));
        assertEquals(3,judge.getSubmitId());
    }

    @Test
    public void addJudge() {
        Judge judge = new Judge();
        judge.setPid(new Long((long)1));
        judge.setUid(1);
        judge.setUsername("alice");
        judge.setCode("1234");
        boolean t =judgeService.addJudge(judge);
        assertEquals(true, t);
    }

    @Test
    public void submitProblemJudge() {
    }
    @Test
    public void selectByParam() {
        int userId=1; Long pid=1L; int status=1; Long lid=-1L;
        List<Judge> list = new ArrayList<>();
        list = judgeService.selectByParam(userId, pid, status, lid, null);
        assertEquals(1,list.size());
    }
}