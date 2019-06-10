package com.amadeus.sso.service.impl;

import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.amadeus.sso.dao.TeacherLoginRepository;
import com.amadeus.sso.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherLoginRepository teacherLoginRepository;

    @Override
    public Optional<TeacherLogin> findByTelephoneNumber(String telephoneNumber) {
        return teacherLoginRepository.findByTelephoneNumber(telephoneNumber);
    }
}
