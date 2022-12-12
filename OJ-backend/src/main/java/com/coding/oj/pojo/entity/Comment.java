package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Comment {
    private Long id;

    private Long aid;

    private Integer uid;

    private Long likeCount;

    private Long parentId;

    private Date gmtCreate;

    private Date gmtModified;

    private String content;

}