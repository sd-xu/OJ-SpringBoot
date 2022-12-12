package com.coding.oj.controller;

import com.coding.oj.pojo.entity.User;
import com.coding.oj.pojo.entity.UserHistory;
import com.coding.oj.pojo.entity.UserInfo;
import com.coding.oj.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {
    @Value("${file.staticAccessPath}")
    private  String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private UserService userService;

    // 通过用户名密码登录
    @GetMapping(value = "/loginByUserName")
    public Map<String,Object> loginByUserName(String username, String password)
    {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("user", userService.selectByUserName(username, password));
        return modelMap;
    }

    // 注册
    @PostMapping(value = "/register")
    public Map<String, Object> registerUser(@RequestBody User user)
            throws JsonParseException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", userService.addUser(user));
        return modelMap;
    }

    // 获取所有的用户信息
    @GetMapping(value = "/listUser")
    public Map<String, Object> listUser() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<User> list = userService.getUserList();
        modelMap.put("userList", list);
        return modelMap;
    }

    // 获取当前用户的历史信息
    @GetMapping(value = "/getUserHistory")
    public Map<String, Object> getUserHistory(Integer userId) {
        Map<String, Object> modelMap = new HashMap<>();
        UserHistory userHistory = userService.getUserHistory(userId);
        modelMap.put("user", userHistory);
        return modelMap;
    }

    // 获取用户界面的展示信息
    @GetMapping(value = "/getUserInfo")
    public Map<String, Object> getUserInfo(Integer userId, String username) {
        Map<String, Object> modelMap = new HashMap<>();
        UserInfo userInfo = userService.getUserInfo(userId, username);
        modelMap.put("user", userInfo);
        return modelMap;
    }

    // 上传用户头像
    @PostMapping(value = "/postUserImage")
    public Map<String, Object> postUserImage(@RequestParam("file") MultipartFile headerImg, @RequestParam Map<String,Object> params) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();

        String username = (String) params.get("username");
        if(!headerImg.isEmpty() && headerImg.getOriginalFilename() != null) {
            String filename = headerImg.getOriginalFilename();
            String hzName = filename.substring(filename.lastIndexOf("."));
            String uuid =  UUID.randomUUID().toString().replace("-","");
            filename = uuid + hzName;
            File filedir = new File(uploadFolder);
            if(!filedir.exists()){
                filedir.mkdirs();   // 判断目录是否存在，不存在直接创建
            }
            headerImg.transferTo(new File(uploadFolder, filename));
            String url = staticAccessPath + filename; // 对应的url有后缀
            boolean effectNum = userService.insertHeadImg(username, url);
            if(effectNum)
                modelMap.put("url", url);
            else
                modelMap.put("fail", false);
        }
        else
            modelMap.put("fail", null);
        return modelMap;
    }

    // 获取用户站内排名
    @GetMapping(value= "/getRank")
    public Map<String, Object> getRank(Integer rankSize) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Map<?, ?>> rankList = userService.getRank(rankSize);
        modelMap.put("rankList", rankList);
        return modelMap;
    }

    // 获取用户某一天通过的题目总数
    @GetMapping(value = "/getSolvedProblemNumInDate")
    public Map<String, Object> getSolvedProblemNumByDate(Integer userId) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Map<?, ?>> solveList = userService.getSolvedProblemNumInDate(userId);
        modelMap.put("solveList", solveList);
        return modelMap;
    }

    // 修改用户简介
    @GetMapping(value = "/addUserDescriptionInfo")
    public Map<String, Object> addUserDescriptionInfo(Integer userId,String description) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success", userService.addUserDescription(userId,description));
        return modelMap;
    }

    // 修改用户性别
    @GetMapping(value = "/updateSexInfo")
    public Map<String, Object> updateSexInfo(Integer userId,Integer gender) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        modelMap.put("success", userService.updateUserGender(userId,gender));
        return modelMap;
    }
}
