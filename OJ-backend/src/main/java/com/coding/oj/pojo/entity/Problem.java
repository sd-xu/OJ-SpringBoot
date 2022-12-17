package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Problem {

    private Long id;

    private String title;

    private String description;

    private String input;

    private String output;

    private String samples;

    private Integer difficulty;

    private String hint;

    private Integer timeLimit;

    private Integer memoryLimit;

    private Integer stackLimit;

    private Date gmtCreate;

    private Date gmtModified;

}