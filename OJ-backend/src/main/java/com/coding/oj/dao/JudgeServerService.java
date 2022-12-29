package com.coding.oj.dao;

import com.coding.oj.pojo.entity.JudgeServer;

import java.util.List;

public interface JudgeServerService {
    boolean release(Integer id);

    JudgeServer selectById(Integer id);

    boolean updateById(JudgeServer judgeServer);

    List<JudgeServer> getList();

}
