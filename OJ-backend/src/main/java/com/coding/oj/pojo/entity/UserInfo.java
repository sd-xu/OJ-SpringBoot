package com.coding.oj.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserInfo {
    // 用户名
    private String username;
    // 性别
    private Integer gender;
    // 用户的介绍
    private String description;
    // 用户的答题日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date answerDate;
    // 该天做出的题目数量
    private Integer count;
    // 用户头像
    private String imageUrl;
}
