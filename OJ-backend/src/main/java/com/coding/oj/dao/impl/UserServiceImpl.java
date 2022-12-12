package com.coding.oj.dao.impl;

import com.coding.oj.mapper.ContestMapper;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.mapper.UserMapper;
import com.coding.oj.pojo.entity.User;
import com.coding.oj.pojo.entity.UserHistory;
import com.coding.oj.pojo.entity.UserInfo;
import com.coding.oj.dao.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private ContestMapper contestMapper;

    @Override
    @Transactional
    public User selectByUserName(String username, String password) {

        return userMapper.selectByUserName(username);
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        if (user.getUsername() != null && !"".equals(user.getUsername())) {
            if (user.getPassword()!=null && !"".equals(user.getPassword())) {
                int effectedNum = userMapper.insert(user);
                return effectedNum > 0;
            }
        }
        return false;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public UserHistory getUserHistory(int userId) {
        UserHistory userHistory = new UserHistory();
        int problem_num = judgeMapper.getProblemNum(userId);
        int contest_num = contestMapper.getContestNum(userId);
        Date last_submit = judgeMapper.getLastSubmit(userId);
        String language = judgeMapper.getUsualLanguage(userId);
        int difficulty = judgeMapper.getDifficulty(userId);
        String area = "";
        switch (difficulty){
            case 0:
                area = "简单"; break;
            case 1:
                area = "中等"; break;
            case 2:
                area = "困难"; break;
        }
        int max_submit = judgeMapper.getMaxSubmit(userId);
        userHistory.setProblem_num(problem_num);
        userHistory.setContest_num(contest_num);
        userHistory.setLast_submit(last_submit);
        userHistory.setLanguage(language);
        userHistory.setArea(area);
        userHistory.setMax_submit(max_submit);
        return userHistory;
    }

    @Override
    public UserInfo getUserInfo(int userId, String username) {
        UserInfo userInfo = new UserInfo();
        User user;
        if(username != null)
            user = userMapper.selectByUserName(username);
        else
            user = userMapper.selectByPrimaryKey(userId);
        userInfo.setUsername(username);
        userInfo.setGender(user.getGender());
        userInfo.setDescription(user.getDescription());
        userInfo.setImageUrl(userInfo.getImageUrl());
        userInfo.setAnswerDate(judgeMapper.getLastSubmit(userId));
        userInfo.setCount(judgeMapper.lastDateCount(userId));
        return userInfo;
    }

    @Override
    public List<Map<?, ?>> getRank(int rankSize) {
        return judgeMapper.getRank(rankSize);
    }

    @Override
    public boolean insertHeadImg(String username,String url) {
        int effectNum = userMapper.updateUrlByUserName(username, url);
        return effectNum > 0;
    }

    @Override
    public List<Map<?, ?>> getSolvedProblemNumInDate(Integer userId) {
        return judgeMapper.selectSolvedProblemNumInDate(userId);
    }

    @Override
    public boolean addUserDescription(Integer userId, String description) {
        int effectNum = userMapper.insertUserDescription(userId,description);
        return effectNum > 0;
    }

    @Override
    public boolean updateUserGender(Integer userId, Integer gender) {
        int effectNum = userMapper.updateUserGender(userId,gender);
        return effectNum > 0;
    }

}