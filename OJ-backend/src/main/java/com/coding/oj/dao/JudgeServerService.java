package com.coding.oj.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coding.oj.pojo.entity.JudgeServer;

import java.util.List;

public interface JudgeServerService extends IService<JudgeServer> {
    boolean updateTaskNum(Integer id);

    JudgeServer selectById(Integer id);

    boolean updateById(JudgeServer judgeServer);

    List<JudgeServer> getList();

}
