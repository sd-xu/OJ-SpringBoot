package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LikeArticle {

    private Long id;

    private Long aid;

    private Integer uid;

    private Date gmtCreate;

    private Date gmtModified;

}