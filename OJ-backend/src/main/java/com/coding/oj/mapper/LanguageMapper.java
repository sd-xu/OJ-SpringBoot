package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.Language;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LanguageMapper {
    int deleteByPrimaryKey(Long lid);

    int insert(Language record);

    Language selectByPrimaryKey(Long lid);

    List<Language> selectAll();

    int updateByPrimaryKey(Language record);

    String selectLanguageByLid(Long lid);
}