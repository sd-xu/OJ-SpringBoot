package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private Integer gender;

    private String email;

    private String description;

    private String imageUrl;

    private Date gmtCreate;

    private Date gmtModified;

}