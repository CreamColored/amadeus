package com.amadeus.web.dao;

import com.amadeus.framework.domain.teacher.TeacherInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherInfoMapper {
    List<TeacherInfo> findAllTeacherInfo();

    int getTotalNumber();
}
