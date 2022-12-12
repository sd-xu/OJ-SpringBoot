package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Status {
    private Integer id;

    private String status;

    private Integer value;

    private Date gmtCreate;

    private Date gmtModified;

}