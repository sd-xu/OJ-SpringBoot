package com.coding.oj.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.oj.mapper.ProblemMapper;
import com.coding.oj.pojo.entity.Problem;
import com.coding.oj.dao.ProblemEntityService;
import org.springframework.stereotype.Service;

@Service
public class ProblemEntityServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemEntityService {

}
