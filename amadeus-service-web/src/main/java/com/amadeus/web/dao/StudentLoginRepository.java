package com.amadeus.web.dao;

import com.amadeus.framework.domain.student.StudentLogin;

public interface StudentLoginRepository extends CrudRepository<StudentLogin,String> {
    int deleteByStudentId(String studentId);
}
