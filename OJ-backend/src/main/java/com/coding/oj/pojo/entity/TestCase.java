package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestCase {
    private Long id;

    private Long pid;

    private String inputFolderPath;

    private String outputFolderPath;

    private Date gmtCreate;

    private Date gmtModified;

}