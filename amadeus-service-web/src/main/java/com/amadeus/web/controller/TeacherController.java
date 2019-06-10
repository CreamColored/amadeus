package com.amadeus.web.controller;

import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/teacher")
@Api(value = "教师管理", tags = "教师管理")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "分页查询教师登录信息", notes = "分页查询")
    @GetMapping("/findAllTeacherLogin/{currentPage}/{pageSize}")
    public QueryResponseResult findAllTeacherLogin(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return teacherService.findAllTeacherLogin(currentPage, pageSize);
    }

    @ApiOperation(value = "分页查询教师基本信息", notes = "分页查询")
    @GetMapping("/findAllTeacherInfo/{currentPage}/{pageSize}")
    public QueryResponseResult findAllTeacherInfo(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return teacherService.findAllTeacherInfo(currentPage, pageSize);
    }

    @ApiOperation(value = "获取TeacherLogin总数据数量", notes = "分页查询")
    @GetMapping("/getTotalTeacherLoginNumber")
    public int getTotalTeacherLoginNumber() {
        return teacherService.getTotalTeacherLoginNumber();
    }

    @ApiOperation(value = "获取TeacherInfo总数据数量", notes = "分页查询")
    @GetMapping("/getTotalTeacherInfoNumber")
    public int getTotalTeacherInfoNumber() {
        return teacherService.getTotalTeacherInfoNumber();
    }

    @ApiOperation(value = "修改教师账户状态", notes = "参数为TeacherLogin，返回值为ResponseResult")
    @PutMapping("/updateAccountState")
    public ResponseResult updateAccountState(TeacherLogin teacherLogin) {
        return teacherService.updateAccountState(teacherLogin.getTeacherId(), teacherLogin.getAccountState());
    }

    @ApiOperation(value = "删除教师账号", notes = "参数为teacherId，返回值为ResponseResult")
    @DeleteMapping("/delete/{teacherId}")
    public ResponseResult delete(@PathVariable("teacherId") String teacherId) {
        return teacherService.deleteByTeacherId(teacherId);
    }

}
