package com.amadeus.framework.exception;

import com.amadeus.framework.model.response.ResultCode;

public class CustomException extends RuntimeException{
    //错误代码
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
