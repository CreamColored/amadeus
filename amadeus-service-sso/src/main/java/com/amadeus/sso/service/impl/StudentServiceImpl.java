package com.amadeus.sso.service.impl;

import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.sso.dao.StudentInfoRepository;
import com.amadeus.sso.dao.StudentLoginRepository;
import com.amadeus.sso.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("studentService")
@CacheConfig(cacheNames = "studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    public Optional<StudentLogin> findByTelephoneNumber(String telephoneNumber) {
        return studentLoginRepository.findByTelephoneNumber(telephoneNumber);
    }

    @Override
    public Optional<StudentLogin> findByEmail(String email) {
        return studentLoginRepository.findByEmail(email);
    }

    @Override
    public boolean existsStudentLoginByTelephoneNumber(String telephoneNumber) {
        return studentLoginRepository.existsStudentLoginByTelephoneNumber(telephoneNumber);
    }

    @Override
    public boolean existsStudentLoginByEmail(String email) {
        return studentLoginRepository.existsStudentLoginByEmail(email);
    }

    @Override
    public StudentLogin saveStudentLogin(StudentLogin studentLogin) {
        return studentLoginRepository.save(studentLogin);
    }

    @Override
    public Optional<StudentInfo> findStudentInfoByStudentLogin(StudentLogin studentLogin) {
        return studentInfoRepository.findStudentInfoByStudentLogin(studentLogin);
    }


    @Override
    public StudentInfo saveStudentInfo(StudentInfo studentInfo) {
        return studentInfoRepository.save(studentInfo);
    }

    @Override
    public void deleteStudent(StudentLogin studentLogin) {
        studentLoginRepository.delete(studentLogin);
    }

}
