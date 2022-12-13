package com.coding.oj.dao;

import com.coding.oj.mapper.ContestMapper;
import com.coding.oj.mapper.JudgeMapper;
import com.coding.oj.mapper.UserMapper;
import com.coding.oj.pojo.entity.User;
import com.coding.oj.pojo.entity.UserHistory;
import com.coding.oj.pojo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ContestMapper contestMapper;
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByUserName() {

        User user = userService.selectByUserName("alice","123456");
        assertEquals(1,user.getId());
    }

    @Test
    public void addUser(){
        //创建一个区域对象
        User user = new User();
        user.setUsername("古海龙");
        user.setPassword("2345");
        user.setGender(0);
        user.setEmail("125611");
        user.setImageUrl("1");
        List<User> userList = userService.getUserList();
        //将该对象实例添加入库
        boolean t =userService.addUser(user);
        //检测影响行数
        assertEquals(true, t);
        //校验总数是否+1
        List<User> userList1 = userService.getUserList();
        assertEquals(userList.size()+1, userList1.size());
    }

    @Test
    public void getUserList(){
        List<User> userList = userService.getUserList();
        assertEquals(1,userList.size());
    }

    @Test
    public void getUserHistory(){
        System.out.println(userService.getUserHistory(2));
    }

    @Test
    public void getUserInfo() {
        int userId = 1;
        String username = "1";
        UserInfo userInfo = new UserInfo();
        User user = userMapper.selectByUserName(username);
        System.out.println(user);
//        userInfo.setUsername(username);
//        userInfo.setGender(user.getGender());
//        userInfo.setDescription(user.getDescription());
//        userInfo.setImageUrl(userInfo.getImageUrl());
//        userInfo.setAnswerDate(judgeMapper.getLastSubmit(userId));
//        userInfo.setCount(judgeMapper.lastDateCount(userId));
//        assertEquals(1, userInfo.getCount());
    }

    @Test
    public void postPersonalImage(){

    }

    @Test
    public void getRank(){
       List<Map<?, ?>>  rankMap = userService.getRank(2);
        System.out.println(rankMap);
    }

    @Test
    public void getSolvedProblemNumInDate(){
        List<Map<?, ?>> mapList = judgeMapper.selectSolvedProblemNumInDate(2);
        System.out.println(mapList);
    }

    @Test
    public void updateUserGender(){
        int effectNum = userMapper.insertUserDescription(1,"zzzzz");
        assertEquals(1,effectNum);
    }

}
