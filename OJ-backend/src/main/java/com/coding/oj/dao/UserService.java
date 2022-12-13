package com.coding.oj.dao;

import com.coding.oj.pojo.entity.User;
import com.coding.oj.pojo.entity.UserHistory;
import com.coding.oj.pojo.entity.UserInfo;

import java.util.List;
import java.util.Map;


public interface UserService {
    // 通过用户名获取信息
    User selectByUserName(String username, String password);

    // 添加用户
    boolean addUser(User user);

    // 获取所有用户信息
    List<User> getUserList();

    // 获取单个用户的历史信息
    UserHistory getUserHistory(int userId);

    UserInfo getUserInfo(int userId, String username);

    List<Map<?, ?>> getRank(int rankSize);

    boolean insertHeadImg(String username,String url);

    List<Map<?, ?>> getSolvedProblemNumInDate(Integer userId);

    boolean addUserDescription(Integer userId, String description);

    boolean updateUserGender(Integer userId, Integer gender);

    User getUserById(Integer userId);
}
