package com.coding.oj.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.dao.JudgeCaseEntityService;
import com.coding.oj.mapper.JudgeCaseMapper;
import com.coding.oj.pojo.entity.JudgeCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JudgeCaseEntityServiceImpl implements JudgeCaseEntityService {

    @Autowired
    private JudgeCaseMapper judgeCaseMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveBatch(List<JudgeCase> judgeCaseList) {
        return judgeCaseMapper.insertBatch(judgeCaseList) > 0; // 或者按数组长度 ?
    }

}
