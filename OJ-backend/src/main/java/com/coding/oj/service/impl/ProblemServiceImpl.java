package com.coding.oj.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.coding.oj.mapper.ProblemMapper;
import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.service.ProblemService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Problem> selectAll() {
        return problemMapper.selectAll();
    }

    @Override
    public boolean addProblem(Problem problem) {
        int effectedNum = problemMapper.insert(problem);
        if (effectedNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Problem selectProblemById(Long id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    @Override
    public Problem selectProblem(String index) {
       // 如果全为数字（非负数）,说明这是题目的编号
       if(CharSequenceUtil.isNumeric(index)){
           return selectProblemById(new Long((long)Integer.parseInt(index)));
       }
       else
           return selectProblemByTitle(index);
    }

    @Override
    public List<Problem> getProblems(int pageNum, int pageSize) {
        // 查询功能之前开启分页功能
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Problem> list = problemMapper.selectAll();
        // 查询功能之后可以获取分页相关的所有数据
        PageInfo<Problem> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public Problem selectProblemByTitle(String title) {
        return problemMapper.selectByTitle(title);
    }
}
