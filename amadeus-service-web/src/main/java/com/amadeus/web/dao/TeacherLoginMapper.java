package com.amadeus.web.dao;

import com.amadeus.framework.domain.teacher.TeacherLogin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherLoginMapper {

    List<TeacherLogin> findAllTeacherLogin();

    int updateAccountState(String teacherId, int accountState);

    int getTotalNumber();
}
