package com.amadeus.sso.dao;

import com.amadeus.framework.domain.teacher.TeacherLogin;

import java.util.Optional;

public interface TeacherLoginRepository extends CrudRepository<TeacherLogin, String> {
    Optional<TeacherLogin> findByTelephoneNumber(String telephoneNumber);
}
