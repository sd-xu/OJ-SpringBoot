package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Problem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemMapperTest {
    @Autowired
    private  ProblemMapper problemMapper;
    @Test
    public void selectAll() {
        List<Problem> problemList = problemMapper.selectAll();
        assertEquals(1,problemList.size());
    }
    @Test
    public void addProblem(){
        //创建一个区域对象
        Problem problem =new Problem();
        //懒得写了，要用的时候再测吧
        List<Problem> problemList = problemMapper.selectAll();
        //将该对象实例添加入库
        int t =problemMapper.insert(problem);
        //检测影响行数
        assertEquals(1, t);
        //校验总数是否+1
        List<Problem> problemList1 = problemMapper.selectAll();
        assertEquals(problemList.size()+1, problemList1.size());
    }
}
