package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Developer {

    private Integer id;

    private String nickname;

    private String blog;

    private String email;

    private Date gmtCreate;

    private Date gmtModified;

}