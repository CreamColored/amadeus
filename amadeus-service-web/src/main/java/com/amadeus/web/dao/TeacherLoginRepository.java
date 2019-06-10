package com.amadeus.web.dao;

import com.amadeus.framework.domain.teacher.TeacherLogin;

public interface TeacherLoginRepository extends CrudRepository<TeacherLogin,String> {
    int deleteByTeacherId(String teacherId);
}
