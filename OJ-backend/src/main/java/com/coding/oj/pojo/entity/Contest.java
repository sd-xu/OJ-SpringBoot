package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Contest {
    private Long id;

    private Integer uid;

    private String link;

    private String author;

    private String title;

    private Integer type;

    private Integer difficulty;

    private String description;

    private Integer source;

    private Integer auth;

    private String pwd;

    private Date startTime;

    private Date endTime;

    private Long duration;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

}