package com.amadeus.sso.service;

import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;

import java.util.Optional;

public interface StudentService {

    /**
     * 通过手机号查询用户登录信息
     * @param telephoneNumber 手机号
     * @return Optional
     */
    Optional<StudentLogin> findByTelephoneNumber(String telephoneNumber);

    /**
     * 通过邮箱查询用户登录信息
     * @param email 邮箱
     * @return Optional
     */
    Optional<StudentLogin> findByEmail(String email);

    /**
     * 通过手机号判断用户是否存在
     * @param telephoneNumber 手机号
     * @return boolean
     */
    boolean existsStudentLoginByTelephoneNumber(String telephoneNumber);

    /**
     * 通过邮箱判断用户是否存在
     * @param email 邮箱
     * @return boolean
     */
    boolean existsStudentLoginByEmail(String email);

    /**
     * 保存用户信息
     * @param studentLogin 学生登录信息
     * @return StudentLogin
     */
    StudentLogin saveStudentLogin(StudentLogin studentLogin);

    /**
     * 通过学生id查找学生信息
     * @param studentLogin 学生id
     * @return Optional
     */
    Optional<StudentInfo> findStudentInfoByStudentLogin(StudentLogin studentLogin);

    /**
     * 保存学生信息
     * @param studentInfo 学生信息
     * @return 学生信息
     */
    StudentInfo saveStudentInfo(StudentInfo studentInfo);

    /**
     * 删除学生
     * @param studentLogin 学生登录信息
     */
    void deleteStudent(StudentLogin studentLogin);
}
