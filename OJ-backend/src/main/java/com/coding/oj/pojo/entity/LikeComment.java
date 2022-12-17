package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LikeComment {

    private Long id;

    private Long cid;

    private Integer uid;

    private Date gmtCreate;

    private Date gmtModified;

}