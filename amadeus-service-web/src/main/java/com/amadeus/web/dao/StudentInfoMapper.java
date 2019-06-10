package com.amadeus.web.dao;

import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface StudentInfoMapper {

    List<StudentInfo> findAll();

    int getTotalNumber();

    int updateBalance(Double balance, String studentId);

    int updatePoint(Integer point, String studentId);

    int updateAvatar(String imgUrl, String studentId);

    int recharge(Double money,String studentId);

}
