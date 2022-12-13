package com.coding.oj.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.oj.pojo.entity.JudgeCase;

import java.util.List;

public interface JudgeCaseEntityService {

    boolean saveBatch(List<JudgeCase> judgeCaseList);

}
