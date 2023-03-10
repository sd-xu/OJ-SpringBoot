package com.coding.oj.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 用户代码提交类
 */
@Data
@Accessors(chain = true)
public class SubmitJudgeDTO {

    private Long pid;

    private Integer uid;

    private String username;

    private String language;

    private String code;

}