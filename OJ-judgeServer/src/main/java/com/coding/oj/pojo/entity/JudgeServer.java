package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class JudgeServer {
    private Integer id;

    private String name;

    private String ip;

    private Integer port;

    private String url;

    private Integer cpuCore;

    private Integer taskNumber;

    private Integer maxTaskNumber;

    private Integer status;

    private Boolean isRemote;

    private Boolean cfSubmittable;

    private Date gmtCreate;

    private Date gmtModified;

}