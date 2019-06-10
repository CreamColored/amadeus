package com.amadeus.web.dao;

import com.amadeus.framework.domain.student.StudentCourse;

import java.util.Optional;

public interface StudentCourseRepository extends CrudRepository<StudentCourse,String>{

    Optional<StudentCourse> findByStudentIdAndCourseId(String studentId, String courseId);

}
