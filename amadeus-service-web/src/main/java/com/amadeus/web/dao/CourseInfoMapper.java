package com.amadeus.web.dao;

import com.amadeus.framework.domain.course.CourseInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseInfoMapper {

    List<CourseInfo> findHotCourses();

    List<CourseInfo> findBoutiqueCourses();

    List<CourseInfo> findAllCourseInfo();

    int getTotalNumber();

    int updateCourseState(String courseId, int courseState);

    int deleteCourse(String courseId);

    List<CourseInfo> findCourseByCourseId(String courseId);

    List<CourseInfo> findCoursesByStudentId(String studentId);

}
