package com.amadeus.framework.model.response;

import lombok.ToString;

@ToString
public enum CommonCode implements ResultCode{

    SUCCESS(true,10000,"操作成功!"),
    SUPER_ADMIN_IDENTITY(true,77777,"超级管理员身份"),
    CAMPUS_ADMIN_IDENTITY(true,88888,"院校管理员身份"),

    UNAUTHENTICATED(false,10001,"此操作需要登陆系统!"),
    UNAUTHORISE(false,10002,"权限不足，无权操作!"),
    INVALID_PARAM(false, 10003, "非法参数!"),
    ACCOUNT_EXIST(false, 10004, "账号存在!"),
    VERIFICATION_CODE_NOT_TRUE(false, 10005, "验证码不正确!"),
    ACCOUNT_NOT_EXIST(false,10006,"账号不存在!"),
    PASSWORD_NOT_TRUE(false,10007,"密码不正确!"),
    ACCOUNT_NOT_ACTIVATE(false,10008,"账号未激活!"),
    ACCOUNT_IS_FORBIDDEN(false,10009,"账号已冻结，请与管理员联系!"),
    EMAIL_IS_NULL(false,10010,"请输入邮箱!"),
    INPUT_ACCOUNT_IS_NULL(false,10011,"输入的账号为空!"),
    FILE_IS_NULL(false,10012,"文件为空!"),
    FILE_FORMAT_ERROR(false,10012,"文件格式不正确!"),
    DATABASE_ERROR(false, 10013, "操作数据库失败"),
    FAIL(false,11111,"操作失败!"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试!");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    CommonCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
