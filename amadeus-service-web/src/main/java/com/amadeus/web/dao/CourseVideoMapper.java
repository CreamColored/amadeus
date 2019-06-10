package com.amadeus.web.dao;

import com.amadeus.framework.domain.course.CourseVideo;
import org.springframework.stereotype.Component;

@Component
public interface CourseVideoMapper {
    int insert(CourseVideo courseVideo);
}
