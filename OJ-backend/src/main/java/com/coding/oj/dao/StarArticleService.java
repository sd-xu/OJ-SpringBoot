package com.coding.oj.dao;

import com.coding.oj.pojo.entity.Star;

public interface StarArticleService {
    boolean addStar(Star star);

    boolean StarOrNot(Long aid, int uid);

    boolean dropStar(Star star);
}
