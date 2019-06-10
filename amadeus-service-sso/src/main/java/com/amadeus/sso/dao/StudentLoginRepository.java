package com.amadeus.sso.dao;

import com.amadeus.framework.domain.student.StudentLogin;

import java.util.Optional;

public interface StudentLoginRepository extends CrudRepository<StudentLogin,String>{

    Optional<StudentLogin> findByEmail(String email);

    Optional<StudentLogin> findByTelephoneNumber(String telephoneNumber);

    boolean existsStudentLoginByTelephoneNumber(String telephoneNumber);

    boolean existsStudentLoginByEmail(String email);

}
