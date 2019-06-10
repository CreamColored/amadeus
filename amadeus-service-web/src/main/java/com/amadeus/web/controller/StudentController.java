package com.amadeus.web.controller;

import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.web.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@Api(value = "学生管理", tags = "学生管理")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "分页查询学生基本信息", notes = "分页查询")
    @GetMapping("/findAll/{currentPage}/{pageSize}")
    public QueryResponseResult findAll(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return studentService.findAll(currentPage, pageSize);
    }

    @ApiOperation(value = "获取总数据数量及页数", notes = "分页查询")
    @GetMapping("/getTotalNumber")
    public int getTotalNumber() {
        return studentService.getTotalNumber();
    }

    @ApiOperation(value = "查询学生登录信息", notes = "分页查询")
    @GetMapping("/findAllStudentLogin")
    public QueryResponseResult findAllStudentLogin() {
        return studentService.findAllStudentLogin();
    }

    @ApiOperation(value = "修改学生账户状态", notes = "参数为StudentLogin，返回值为ResponseResult")
    @PutMapping("/updateAccountState")
    public ResponseResult updateAccountState(StudentLogin studentLogin) {
        return studentService.updateAccountState(studentLogin.getStudentId(), studentLogin.getAccountState());
    }

    @ApiOperation(value = "删除学生账号", notes = "参数为studentId，返回值为ResponseResult")
    @DeleteMapping("/delete/{studentId}")
    public ResponseResult delete(@PathVariable("studentId") String studentId) {
        return studentService.deleteByStudentId(studentId);
    }

    @ApiOperation(value = "学生添加课程", notes = "参数为studentCourse，返回值为ResponseResult")
    @PostMapping("/addCourse/{studentId}/{courseId}")
    public ResponseResult addCourse(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        return studentService.addCourse(studentId, courseId);
    }

    @ApiOperation(value = "判断课程是否已添加", notes = "参数为studentId和courseId，返回值为ResponseResult")
    @GetMapping("/hasBought/{studentId}/{courseId}")
    public ResponseResult hasBought(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        return studentService.hasBought(studentId, courseId);
    }

    @ApiOperation(value = "通过余额购买课程", notes = "参数为balance和studentId，返回值为ResponseResult")
    @PutMapping("/buyCourseByBalance/{balance}/{studentId}")
    public ResponseResult buyCourseByBalance(@PathVariable("balance") Double balance, @PathVariable("studentId") String studentId) {
        return studentService.buyCourseByBalance(balance, studentId);
    }

    @ApiOperation(value = "通过积分购买课程", notes = "参数为point和studentId，返回值为ResponseResult")
    @PutMapping("/buyCourseByPoint/{point}/{studentId}")
    public ResponseResult buyCourseByPoint(@PathVariable("point") Integer point, @PathVariable("studentId") String studentId) {
        return studentService.buyCourseByPoint(point, studentId);
    }

    @ApiOperation(value = "上传头像", notes = "参数为MultipartFile，返回值为String")
    @PostMapping("/uploadImage/{studentId}")
    public String uploadImage(@RequestParam MultipartFile file, @PathVariable("studentId") String studentId) {
        return studentService.uploadImage(file, studentId);
    }

    @ApiOperation(value = "修改学生信息", notes = "参数为studentLogin，返回值为ResponseResult")
    @PutMapping("/update")
    public ResponseResult update(StudentLogin studentLogin) {
        return studentService.update(studentLogin);
    }

    @ApiOperation(value = "余额充值", notes = "参数为money跟studentId，返回值为ResponseResult")
    @PutMapping("/recharge/{money}/{studentId}")
    public ResponseResult recharge(@PathVariable("money") Double money, @PathVariable("studentId") String studentId) {
        return studentService.recharge(money, studentId);
    }

    @ApiOperation(value = "发表留言", notes = "参数为content、courseId跟studentId，返回值为ResponseResult")
    @PostMapping("/comment/{content}/{courseId}/{studentId}")
    public ResponseResult comment(@PathVariable("content") String content, @PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId) {
        return studentService.comment(content, courseId, studentId);
    }
}
