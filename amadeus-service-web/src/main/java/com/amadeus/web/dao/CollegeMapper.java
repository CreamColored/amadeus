package com.amadeus.web.dao;

import com.amadeus.framework.domain.college.CollegeInfo;
import org.springframework.stereotype.Component;

@Component
public interface CollegeMapper {
    int updateState(CollegeInfo collegeInfo);

    int deleteByCollegeId(String collegeId);

    int update(CollegeInfo collegeInfo);
}
