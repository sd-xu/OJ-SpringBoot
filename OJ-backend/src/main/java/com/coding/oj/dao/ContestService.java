package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Contest;

import java.util.List;
import java.util.Map;

public interface ContestService {
    List<Contest> getContestList();

    List<Contest> getOurContests(int pageNum, int pageSize);

    List<Map<String,Object>> getContestsByType(int type);
}
