package com.amadeus.api.sso;

import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.domain.admin.AdminLoginResult;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.domain.student.StudentLoginResult;
import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.amadeus.framework.domain.teacher.TeacherLoginResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.model.response.SendCodeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpSession;

@Api(value = "单点登录接口", tags = "提供单点登录、注销功能")
public interface SSOApi {

    @ApiOperation(value = "学生单点登录", notes = "通过手机号或邮箱进行登录")
    StudentLoginResult studentLogin(StudentLogin studentLogin);

    @ApiOperation(value = "教师单点登录", notes = "通过手机号或邮箱进行登录")
    TeacherLoginResult teacherLogin(TeacherLogin teacherLogin);

    @ApiOperation(value = "学生注册", notes = "使用手机号或者邮箱进行注册并对验证码进行验证")
    ResponseResult studentRegister(StudentLogin studentLogin);

    @ApiOperation(value = "查看账号是否存在", notes = "查看账号是否存在")
    ResponseResult studentExist(StudentLogin studentLogin);

    @ApiOperation(value = "管理员登录", notes = "通过邮箱进行登录")
    AdminLoginResult adminLogin(AdminInfo adminInfo);

    @ApiOperation(value = "发送邮箱验证码", notes = "向用户邮箱发送验证码")
    ResponseResult sendEmailCode(String email, HttpSession session);

    @ApiOperation(value = "发送短信验证码", notes = "向用户发送单条短信验证码")
    SendCodeResult sendSingleSms(String telephoneNumber);

    @ApiOperation(value = "添加管理员", notes = "参数为adminInfo，返回值为adminInfo")
    ResponseResult saveAdmin(AdminInfo adminInfo);
}
