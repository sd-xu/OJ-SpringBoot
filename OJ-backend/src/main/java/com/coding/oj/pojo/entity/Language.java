package com.coding.oj.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Language {
    private Long lid;

    private String contentType;

    private String description;

    private String name;

    private String compileCommand;

    private String template;

    private String codeTemplate;

    private Boolean isSpj;

    private String oj;

    private Date gmtCreate;

    private Date gmtModified;

}