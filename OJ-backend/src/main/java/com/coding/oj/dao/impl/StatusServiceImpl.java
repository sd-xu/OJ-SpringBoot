package com.coding.oj.dao.impl;

import com.coding.oj.mapper.StatusMapper;
import com.coding.oj.pojo.entity.Status;
import com.coding.oj.dao.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusMapper statusMapper;
    @Override
    public List<Status> getStatusList() {
        return statusMapper.selectAll();
    }

}
