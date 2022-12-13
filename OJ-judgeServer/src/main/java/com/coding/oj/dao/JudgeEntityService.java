package com.coding.oj.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.oj.pojo.entity.Judge;

/**
 * 服务类
 *
 */
public interface JudgeEntityService {

    // 通过id更新评测信息
    boolean updateById(Judge judge);

    boolean updateStatus(Integer status, Long submitId);

    // 通过id更新未取消的评测信息
    boolean updateValid(Integer status, String judger, Long submitId);

    // 通过提交id获取评测信息
    Judge getBySubmitId(Long submitId);

}
