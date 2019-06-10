package com.amadeus.web.dao;

import com.amadeus.framework.domain.student.StudentLogin;
import org.springframework.stereotype.Component;

@Component
public interface StudentLoginMapper {

    int updateAccountState(String studentId, int accountState);

    int update(StudentLogin studentLogin);

}

