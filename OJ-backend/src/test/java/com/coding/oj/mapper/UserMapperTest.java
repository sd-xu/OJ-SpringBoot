package com.coding.oj.mapper;

import com.coding.oj.pojo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectByUserName() {

        User user = userMapper.selectByUserName("许承灏");
        assertEquals(1,user.getId());
    }

    @Test
    public void addUser(){
        //创建一个区域对象
        User user = new User();
        user.setUsername("徐锶达");
        user.setPassword("2345");
        user.setEmail("1256");
        List<User> userList = userMapper.selectAll();
        //将该对象实例添加入库
        int t =userMapper.insert(user);
        //检测影响行数
        assertEquals(1, t);
        //校验总数是否+1
        List<User> userList1 = userMapper.selectAll();
        assertEquals(userList.size()+1, userList1.size());
    }
    @Test
    public  void getUserList(){
        List<User> userList = userMapper.selectAll();
        assertEquals(3,userList.size());
    }
}
