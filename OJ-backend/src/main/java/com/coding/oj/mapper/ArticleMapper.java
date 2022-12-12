package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Article;
import jdk.internal.net.http.common.Pair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    Article selectByPrimaryKey(Long id);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    List<String> selectSort();

    List<Pair<String, String>> selectSortPercent();

    List<Article> selectRecommendation();
}