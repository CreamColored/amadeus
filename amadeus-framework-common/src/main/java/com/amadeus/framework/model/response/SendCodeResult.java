package com.amadeus.framework.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SendCodeResult implements Response {
    //操作是否成功
    private boolean success = SUCCESS;
    //操作代码
    private int code = SUCCESS_CODE;
    //提示信息
    private String message;
    //tokens
    private String token;

    public SendCodeResult(ResultCode resultCode, String token) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.token = token;
    }
    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }
}
