package com.amadeus.framework.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class FdfsResult implements Response {
    //操作是否成功
    private boolean success = SUCCESS;
    //操作代码
    private int code = SUCCESS_CODE;
    //提示信息
    private String message;
    //fdfs文件系统中的文件位置
    private String filePath;

    public FdfsResult(ResultCode resultCode,String filePath) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.filePath = filePath;
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }
}
