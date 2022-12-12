package com.coding.oj.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.mapper.JudgeCaseMapper;
import com.coding.oj.pojo.entity.JudgeCase;
import com.coding.oj.dao.JudgeCaseService;
import org.springframework.stereotype.Service;

@Service
public class JudgeCaseServiceImpl extends ServiceImpl<JudgeCaseMapper, JudgeCase> implements JudgeCaseService {
}
