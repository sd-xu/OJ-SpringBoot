package com.coding.oj.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 服务实现类
 */
@Service
public class JudgeEntityServiceImpl implements JudgeEntityService {

    @Autowired
    private JudgeMapper judgeMapper;

    @Override
    public boolean updateById(Judge judge) {
        return judgeMapper.updateByPrimaryKey(judge) > 0;
    }

    @Override
    public boolean updateStatus(Integer status, Long submitId) {
        return judgeMapper.updateStatusByPrimaryKey(status, submitId) > 0;
    }

    @Override
    public boolean updateValid(Integer status, String judger, Long submitId) {
        Judge judge = getBySubmitId(submitId);
        if (Objects.equals(judge.getStatus(), Constants.Judge.STATUS_CANCELLED.getStatus())) {
            return false;
        }

        judge.setStatus(status);
        judge.setJudger(judger);
        System.out.println(judgeMapper.updateByPrimaryKey(judge));
        return judgeMapper.updateByPrimaryKey(judge) > 0;
    }

    @Override
    public Judge getBySubmitId(Long submitId) {
        return judgeMapper.selectByPrimaryKey(submitId);
    }

}
