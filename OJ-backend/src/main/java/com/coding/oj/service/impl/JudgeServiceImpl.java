package com.coding.oj.service.impl;

import com.coding.oj.common.exception.*;
import com.coding.oj.common.result.CommonResult;
import com.coding.oj.common.result.ResultStatus;
import com.coding.oj.manager.JudgeManager;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.pojo.dto.SubmitJudgeDTO;
import com.coding.oj.pojo.entity.Judge;
import com.coding.oj.service.JudgeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private JudgeManager judgeManager;

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
    public CommonResult<Judge> submitProblemJudge(SubmitJudgeDTO judgeDto) {
        try {
            return CommonResult.successResponse(judgeManager.submitProblemJudge(judgeDto));
        } catch (StatusForbiddenException e) {
            return CommonResult.errorResponse(e.getMessage(), ResultStatus.FORBIDDEN);
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
    public List<Judge> selectJudgeByLanguage(int userId, Long language) {
        return judgeMapper.selectByUserIdAndLanguage(userId, language);
    }

    @Override
    public List<Judge> selectJudgeByProblemId(int userId, Long pid) {
        return judgeMapper.selectByUserIdAndPid(userId, pid);
    }

    @Override
    public List<Judge> selectByParam(int userId, Long pid, int status, Long lid, String title) {
        return judgeMapper.selectByAllParam(userId, pid, status, lid, title);
    }
}
