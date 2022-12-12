package com.coding.oj.common.exception;

import com.coding.oj.common.result.ResultStatus;

public class MyException extends RuntimeException {
    private int code;

    private String errMsg;

    public MyException(ResultStatus resultStatus) {
        this.code = resultStatus.getStatus();
        this.errMsg = resultStatus.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
