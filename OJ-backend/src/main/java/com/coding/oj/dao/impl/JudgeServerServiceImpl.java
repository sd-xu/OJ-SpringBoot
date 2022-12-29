package com.coding.oj.dao.impl;

import com.coding.oj.mapper.JudgeServerMapper;
import com.coding.oj.pojo.entity.JudgeServer;
import com.coding.oj.dao.JudgeServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServerServiceImpl implements JudgeServerService {
    @Autowired
    private JudgeServerMapper judgeServerMapper;

    public boolean release(Integer id) {
        return judgeServerMapper.updateTaskNum(id) == 1;
    }

    @Override
    public JudgeServer selectById(Integer id) {return judgeServerMapper.selectByPrimaryKey(id);}

    @Override
    public boolean updateById(JudgeServer judgeServer) {
        return judgeServerMapper.updateByPrimaryKey(judgeServer) == 1;
    }

    @Override
    public List<JudgeServer> getList() {
        return judgeServerMapper.selectAll();
    }

}
