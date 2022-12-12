package com.coding.oj.service;

import com.coding.oj.pojo.entity.Star;

public interface StarArticleService {
    boolean addStar(Star star);

    boolean StarOrNot(Long aid, int uid);

    boolean dropStar(Star star);
}
