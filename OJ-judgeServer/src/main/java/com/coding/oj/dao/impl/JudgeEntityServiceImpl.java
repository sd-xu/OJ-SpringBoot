package com.coding.oj.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.dao.JudgeEntityService;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.pojo.entity.Judge;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
public class JudgeEntityServiceImpl extends ServiceImpl<JudgeMapper, Judge> implements JudgeEntityService {

}
