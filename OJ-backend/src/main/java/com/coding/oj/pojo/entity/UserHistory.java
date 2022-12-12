package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserHistory {
    // 已提交题目数量
    private int problem_num;

    // 已参与竞赛数量
    private int contest_num;

    // 网站rank
    private int rank;

    // 上一次提交时间
    private Date last_submit;

    // 常用语言
    private String language;

    // 擅长领域
    private String area;

    // 上一次登录时间
    private Date last_login;

    // 最大提交次数
    private int max_submit;

    // 最高排名
    private int peak;

}
