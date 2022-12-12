package com.coding.oj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.mapper.JudgeCaseMapper;
import com.coding.oj.pojo.entity.JudgeCase;
import com.coding.oj.service.JudgeCaseService;
import org.springframework.stereotype.Service;

@Service
public class JudgeCaseServiceImpl extends ServiceImpl<JudgeCaseMapper, JudgeCase> implements JudgeCaseService {
}
