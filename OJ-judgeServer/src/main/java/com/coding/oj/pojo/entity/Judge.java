package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Judge {

    private Long submitId;

    private Long pid;

    private Integer uid;

    private String username;

    private Date submitTime;

    private Integer status;

    private Boolean share;

    private String errorMessage;

    private Integer time;

    private Integer memory;

    private Integer score;

    private Integer length;

    private String code;

    private String language;

    private String judger;

    private String ip;

    private Long vjudgeSubmitId;

    private String vjudgeUsername;

    private String vjudgePassword;

    private Boolean isManual;

    private Date gmtCreate;

    private Date gmtModified;

}