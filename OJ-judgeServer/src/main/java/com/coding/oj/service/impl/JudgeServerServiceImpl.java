package com.coding.oj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.mapper.JudgeServerMapper;
import com.coding.oj.pojo.entity.JudgeServer;
import com.coding.oj.service.JudgeServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServerServiceImpl extends ServiceImpl<JudgeServerMapper, JudgeServer> implements JudgeServerService {
    @Autowired
    private JudgeServerMapper judgeServerMapper;

    public boolean updateTaskNum(Integer id){
        int effect_num = judgeServerMapper.updateTaskNum(id);
        if(effect_num == 1)  return true;
        else return  false;
    }

    @Override
    public JudgeServer selectById(Integer id) {return judgeServerMapper.selectByPrimaryKey(id);}

    @Override
    public boolean updateById(JudgeServer judgeServer) {
        int effect_num = judgeServerMapper.updateByPrimaryKey(judgeServer);
        if(effect_num == 1)  return true;
        else return  false;
    }

    @Override
    public List<JudgeServer> getList() {
        return judgeServerMapper.selectAll();
    }

}
