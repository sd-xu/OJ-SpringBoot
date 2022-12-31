package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);

    int updateUrlByUserName(@Param("username") String username, @Param("url") String url);

    int insertUserDescription(@Param("uid")Integer userId, @Param("description")String description);

    int updateUserGender(@Param("uid")Integer userId, @Param("gender")Integer gender);

    User selectByUserNameAndPassword(@Param("username")String username, @Param("password")String password);
}