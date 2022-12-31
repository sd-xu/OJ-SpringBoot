package com.coding.oj.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultStatus {

    SUCCESS(200,"成功"),

    FAIL(400,"失败"),

    ACCESS_DENIED(401,"访问受限"),

    FORBIDDEN(403,"拒绝访问"),

    NOT_FOUND(404,"数据不存在"),

    SYSTEM_ERROR(500,"系统错误"),

    DATASOURCE_ERROR(11,"数据库操作失败");

    private int status;

    private String msg;
}