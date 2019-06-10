package com.amadeus.sso.dao;

import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;

import java.util.Optional;

public interface StudentInfoRepository extends CrudRepository<StudentInfo, String> {
    Optional<StudentInfo> findStudentInfoByStudentLogin(StudentLogin studentLogin);
}
