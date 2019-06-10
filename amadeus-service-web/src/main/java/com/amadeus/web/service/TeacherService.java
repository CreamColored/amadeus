package com.amadeus.web.service;

import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;

public interface TeacherService {

    QueryResponseResult findAllTeacherInfo(int currentPage, int pageSize);

    QueryResponseResult findAllTeacherLogin(int currentPage, int pageSize);

    ResponseResult deleteByTeacherId(String teacherId);

    ResponseResult updateAccountState(String teacherId, int accountState);

    int getTotalTeacherInfoNumber();

    int getTotalTeacherLoginNumber();

}
