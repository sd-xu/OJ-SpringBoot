package com.coding.oj.service.impl;

import com.coding.oj.mapper.DeveloperMapper;
import com.coding.oj.pojo.entity.Developer;
import com.coding.oj.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private DeveloperMapper developerMapper;

    @Override
    public List<Developer> getDeveloperList() {
        return developerMapper.selectAll();
    }
}
