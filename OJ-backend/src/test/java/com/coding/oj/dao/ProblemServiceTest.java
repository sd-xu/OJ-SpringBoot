package com.coding.oj.dao;

import com.coding.oj.mapper.ProblemMapper;
import com.coding.oj.pojo.entity.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static cn.hutool.core.text.CharSequenceUtil.isNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemServiceTest {
    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemMapper problemMapper;

    @Test
    public void selectAll() {
        List<Problem> problemList = problemService.selectAll();
        assertEquals(3,problemList.size());
    }

    @Test
    public void addProblem(){
        //创建一个区域对象
        Problem problem =new Problem();
        //懒得写了，要用的时候再测吧
        List<Problem> problemList = problemService.selectAll();
        //将该对象实例添加入库
        boolean t =problemService.addProblem(problem);
        //检测影响行数
        assertEquals(true, t);
        //校验总数是否+1
        List<Problem> problemList1 = problemService.selectAll();
        assertEquals(problemList.size()+1, problemList1.size());
    }

    @Test
    public void selectProblemById() {
        Long id = new Long((long)1);
        Problem problem = problemService.selectProblemById(id);
        assertEquals(1,problem.getId());
    }

    @Test
    public void selectProblem() {
        String index = "1";
        //如果全为数字（非负数）,说明这是题目的编号
        Problem problem;
        if (isNumeric(index)) {
            problem = problemService.selectProblemById(new Long((long) Integer.parseInt(index)));
            assertEquals(1, problem.getId());
        } else
            problem = problemService.selectProblemByTitle(index);
        assertEquals(1, problem.getId());
    }

    @Test
    public void selectProblemByTitle() {
        String title = "1";
        Problem problem =  problemMapper.selectByTitle(title);
        assertEquals(1,problem.getId());
    }

    @Test
    public void getProblems() {
        List<Problem> list = problemService.getProblems(2, 1);
        System.out.println(list);
    }
}