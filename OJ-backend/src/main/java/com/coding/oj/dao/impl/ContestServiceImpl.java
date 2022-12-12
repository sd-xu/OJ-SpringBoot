package com.coding.oj.dao.impl;

import com.coding.oj.mapper.ContestMapper;
import com.coding.oj.pojo.entity.Contest;
import com.coding.oj.dao.ContestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContestServiceImpl implements ContestService {
    @Autowired
    private ContestMapper contestMapper;

    @Override
    public List<Contest> getContestList() {
        return contestMapper.selectAll();
    }

    @Override
    public List<Contest> getOurContests(int pageNum, int pageSize) {
        // 查询功能之前开启分页功能
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Contest> list = contestMapper.selectByType(0);
        // 查询功能之后可以获取分页相关的所有数据
        PageInfo<Contest> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Map<String,Object>> getContestsByType(int type) {
        List<Contest> contestList = contestMapper.selectByType(type);
        List<Map<String,Object>> contestInfoList = new ArrayList<>();
        for(Contest contest :contestList){
            Map<String,Object> contestInfo = new HashMap<>();
            contestInfo.put("start_time",contest.getStartTime());
            contestInfo.put("title",contest.getTitle());
            contestInfo.put("link",contest.getLink());
            contestInfo.put("type",contest.getType());
            contestInfoList.add(contestInfo);
        }
        return contestInfoList;
    }

}
