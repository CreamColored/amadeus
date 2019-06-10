package com.amadeus.sso.service;

import com.amadeus.framework.domain.teacher.TeacherLogin;

import java.util.Optional;

public interface TeacherService {
    /**
     * 通过手机号查询用户登录信息
     * @param telephoneNumber 手机号
     * @return Optional
     */
    Optional<TeacherLogin> findByTelephoneNumber(String telephoneNumber);
}
