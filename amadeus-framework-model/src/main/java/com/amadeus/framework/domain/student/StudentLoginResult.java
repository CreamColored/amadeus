package com.amadeus.framework.domain.student;

import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.Response;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class StudentLoginResult implements Response {
    //操作是否成功
    private boolean success = SUCCESS;
    //操作代码
    private int code = SUCCESS_CODE;
    //提示信息
    private String message;

    private StudentLogin studentLogin;

    public StudentLoginResult(ResultCode resultCode, StudentLogin studentLogin) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.studentLogin = studentLogin;
    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(CommonCode.FAIL);
    }
}
