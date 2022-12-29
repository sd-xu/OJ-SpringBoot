package com.coding.oj.dao.impl;

import com.coding.oj.mapper.StarMapper;
import com.coding.oj.pojo.entity.Star;
import com.coding.oj.dao.StarArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarArticleServiceImpl implements StarArticleService {
    @Autowired
    private StarMapper starMapper;

    @Override
    public boolean addStar(Star star) {
        if(star.getAid() == null || star.getUid() == null) {
            return false;
        }
        if (StarOrNot(star.getAid(), star.getUid())) {
            return false; // 已收藏
        }
        int effectNum = starMapper.insert(star);
        return (effectNum > 0);
    }

    @Override
    public boolean StarOrNot(Long aid, int uid) {
        List<Star> list =  starMapper.selectByAidAndUid(aid, uid);
        return (!list.isEmpty());
    }

    @Override
    public boolean dropStar(Star star) {
        int effectNum = starMapper.deleteByAidAndUid(star.getAid(), star.getUid());
        return (effectNum > 0);
    }
}
