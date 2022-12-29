package com.coding.oj.common;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class Result implements Serializable {
    private Integer status;   // 状态码

    private String data;   // 返回的数据

    private String msg;    // 自定义信息

    public Result(Integer status, String data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Result() {

    }

    public static Result successResponseall(String data, String msg) {
        return new Result(ResultStatus.SUCCESS.getStatus(), data, msg);
    }


    /**
     * 成功的结果
     *
     * @param data 返回结果
     */
    public static Result successResponsedata(String data) {
        return new Result(ResultStatus.SUCCESS.getStatus(), data, "success");
    }

    /**
     * 成功的结果
     *
     * @param msg 返回信息
     */
    public static Result successResponsemsg(String msg) throws JSONException {
        return new Result(ResultStatus.SUCCESS.getStatus(), null, msg);
    }

    /**
     * 成功的结果
     */
    public static Result successResponse() {
        return new Result(ResultStatus.SUCCESS.getStatus(), null, "success");
    }



}
