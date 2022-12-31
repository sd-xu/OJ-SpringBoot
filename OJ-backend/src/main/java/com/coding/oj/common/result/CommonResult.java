package com.coding.oj.common.result;

import lombok.Data;


@Data
public class CommonResult<T>  {

    private final Integer status;   // 状态码

    private final T data;   // 返回的数据

    private final String msg;    // 自定义信息

    public CommonResult(Integer status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功的结果
     *
     * @param data 返回结果
     * @param msg  返回信息
     */
    public static <T> CommonResult<T> successResponse(T data, String msg) {
        return new CommonResult<>(ResultStatus.SUCCESS.getStatus(), data, msg);
    }


    /**
     * 成功的结果
     *
     * @param data 返回结果
     */
    public static <T> CommonResult<T> successResponse(T data) {
        return new CommonResult<T>(ResultStatus.SUCCESS.getStatus(), data, "success");
    }

    /**
     * 成功的结果
     *
     * @param msg 返回信息
     */
    public static <T> CommonResult<T> successResponse(String msg) {
        return new CommonResult<>(ResultStatus.SUCCESS.getStatus(), null, msg);
    }

    /**
     * 成功的结果
     */
    public static <T> CommonResult<T> successResponse() {
        return new CommonResult<>(ResultStatus.SUCCESS.getStatus(), null, "success");
    }


    /**
     * 失败的结果，无异常
     *
     * @param msg 返回信息
     */
    public static <T> CommonResult<T> errorResponse(String msg) {
        return new CommonResult<>(ResultStatus.FAIL.getStatus(), null, msg);
    }

    public static <T> CommonResult<T> errorResponse(ResultStatus resultStatus) {
        return new CommonResult<>(resultStatus.getStatus(), null, resultStatus.getMsg());
    }

    public static <T> CommonResult<T> errorResponse(String msg, ResultStatus resultStatus) {
        return new CommonResult<>(resultStatus.getStatus(), null, msg);
    }

    public static <T> CommonResult<T> errorResponse(String msg, Integer status) {
        return new CommonResult<>(status, null, msg);
    }

}