package com.amadeus.web.dao;

import com.amadeus.framework.domain.course.CourseComment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseCommentMapper {

    List<CourseComment> findCommentByCourseId(String courseId);

    int insertComment(CourseComment courseComment, String courseId, String studentId);

}
