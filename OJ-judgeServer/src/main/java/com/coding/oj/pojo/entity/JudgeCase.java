package com.coding.oj.pojo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
public class JudgeCase {
    private Long id;

    private Long submitId;

    private Integer uid;

    private Long pid;

    private Long caseId;

    private Integer status;

    private Integer time;

    private Integer memory;

    private Integer score;

    private Integer groupNum;

    private Integer seq;

    private String mode;

    private String inputData;

    private String outputData;

    private String userOutput;

    private Date gmtCreate;

    private Date gmtModified;

}