package com.coding.oj.dao.impl;

import com.coding.oj.common.exception.*;
import com.coding.oj.common.result.ResultStatus;
import com.coding.oj.manager.JudgeManager;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.dao.JudgeEntityService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeEntityServiceImpl implements JudgeEntityService {

    @Autowired
    private JudgeMapper judgeMapper;

    @Override
    public boolean updateById(Judge judge) {
        // 空值判断,先不写，没准前端可以进行判断
       try{
           // 更新区域信息
           int effectedNum = judgeMapper.updateByPrimaryKey(judge);
           if(effectedNum > 0) {
               return true;
           } else throw new MyException(ResultStatus.DATASOURCE_ERROR);    // 报数据库操作失败的异常
       }
       catch (Exception e){
           throw e;
       }
    }
    
    @Override
    public boolean updateBySubmitId(Long submitId) {
        //空值判断,先不写，没准前端可以进行判断
        try{
            //更新区域信息
            int effectedNum = judgeMapper.updateBySubmitId(submitId);
            if (effectedNum > 0) {
                return true;
            } else throw new MyException(ResultStatus.DATASOURCE_ERROR);    // 报数据库操作失败的异常
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public Judge getBySubmitId(Long submitId) {
        return judgeMapper.selectBySubmitId(submitId);
    }

    @Override
    public boolean addJudge(Judge judge) {
        int effectedNum = judgeMapper.insert(judge);
        if (effectedNum > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Judge> selectAll() {
        return judgeMapper.selectAll();
    }

    @Override
    public List<Judge> selectJudgeByStatus(int userId, int status) {
        return judgeMapper.selectByUserIdAndStatus(userId, status);
    }

    @Override
    public List<Judge> selectJudgeByUserId(int userId) {
        return judgeMapper.selectByUserId(userId);
    }

    @Override
    public List<Judge> selectJudgeByLanguage(int userId, String language) {
        return judgeMapper.selectByUserIdAndLanguage(userId, language);
    }

    @Override
    public List<Judge> selectJudgeByProblemId(int userId, Long pid) {
        return judgeMapper.selectByUserIdAndPid(userId, pid);
    }

    @Override
    public List<Judge> selectByParam(int userId, Long pid, Integer status, String language, String title) {
        return judgeMapper.selectByAllParam(userId, pid, status, language, title);
    }

}
