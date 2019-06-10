package com.amadeus.web.service;

import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    QueryResponseResult findAll(int currentPage, int pageSize);

    QueryResponseResult findAllStudentLogin();

    ResponseResult deleteByStudentId(String studentId);

    ResponseResult updateAccountState(String studentId, int accountState);

    int getTotalNumber();

    ResponseResult addCourse(String studentId,String courseId);

    ResponseResult hasBought(String studentId, String courseId);

    ResponseResult buyCourseByBalance(Double balance, String studentId);

    ResponseResult buyCourseByPoint(Integer point, String studentId);

    String uploadImage(MultipartFile file,String studentId);

    ResponseResult update(StudentLogin studentLogin);

    ResponseResult recharge(Double money, String studentId);

    ResponseResult comment(String content, String courseId, String studentId);
}
