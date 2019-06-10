package com.amadeus.web.dao;

import com.amadeus.framework.domain.course.CourseImage;
import org.springframework.stereotype.Component;

@Component
public interface CourseImageMapper {
    int insert(CourseImage courseImage);
}
