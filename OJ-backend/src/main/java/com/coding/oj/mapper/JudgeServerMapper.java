package com.coding.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coding.oj.pojo.entity.JudgeServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JudgeServerMapper extends BaseMapper<JudgeServer> {

    int deleteByPrimaryKey(Integer id);

    int insert(JudgeServer record);

    JudgeServer selectByPrimaryKey(Integer id);

    List<JudgeServer> selectAll();

    int updateByPrimaryKey(JudgeServer record);

    int updateTaskNum(Integer id);
}