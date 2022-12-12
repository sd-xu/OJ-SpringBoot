package com.coding.oj.service;

import com.coding.oj.pojo.entity.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseServiceTest {

    @Autowired
    private TestCaseService testCaseService;
    @Test
    public void selectAll() {
        List<TestCase> testCaseList = testCaseService.selectAll();
        assertEquals(3,testCaseList.size());
    }

    @Test
    public void addTestCase() {
        //创建一个区域对象
        TestCase testCase = new TestCase();
        //懒得写了，要用的时候再测吧
        List<TestCase> testCaseList = testCaseService.selectAll();
        //将该对象实例添加入库
        boolean t =testCaseService.addTestCase(testCase);
        //检测影响行数
        assertEquals(true, t);
        //校验总数是否+1
        List<TestCase> testCaseList1 = testCaseService.selectAll();
        assertEquals(testCaseList.size()+1, testCaseList1.size());
    }
    @Test
    public void selectByProblem(){
        Long pid = new Long((long)1);
        List<TestCase> testCaseList =testCaseService.selectByProblem(pid);
        assertEquals(1,testCaseList.size());
    }
}