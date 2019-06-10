package com.amadeus.web.service;

import com.amadeus.framework.domain.course.CourseImage;
import com.amadeus.framework.domain.course.CourseInfo;
import com.amadeus.framework.domain.course.CourseVideo;
import com.amadeus.framework.model.response.QueryResponseResult;
import com.amadeus.framework.model.response.ResponseResult;

public interface CourseService {

    ResponseResult insert(CourseInfo courseInfo);

    QueryResponseResult findHotCourses(int currentPage, int pageSize);

    QueryResponseResult findBoutiqueCourses(int currentPage, int pageSize);

    QueryResponseResult findAllCourseInfo(int currentPage, int pageSize);

    int getTotalNumber();

    ResponseResult updateCourseState(String courseId, int courseState);

    ResponseResult deleteCourse(String courseId);

    ResponseResult uploadVideo(CourseVideo courseVideo, String courseId);

    ResponseResult uploadImage(CourseImage courseImage, String courseId);

    QueryResponseResult findCourseByCourseId(String courseId);

    QueryResponseResult findCommentByCourseId(String courseId);

    QueryResponseResult findCoursesByStudentId(String studentId, int currentPage, int pageSize);
}
