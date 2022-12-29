package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Article {

    private Long id;

    private Integer uid;

    private String title;

    private String content;

    private String sort;

    private String label;

    private String imageUrl;

    private Long likeCount;

    private Long commentCount;

    private Long starCount;

    private Date gmtCreate;

    private Date gmtModified;

}