package com.amadeus.framework.exception;


import com.amadeus.framework.model.response.ResultCode;

public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }

}
