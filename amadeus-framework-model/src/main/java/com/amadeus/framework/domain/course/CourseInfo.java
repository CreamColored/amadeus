package com.amadeus.framework.domain.course;

import com.amadeus.framework.domain.college.CollegeInfo;
import com.amadeus.framework.domain.order.OrderInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.domain.teacher.TeacherLogin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_info", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = -6836997188525730559L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String courseId;

    private String courseName;

    private String description;

    private int favorNumber;

    private int clickNumber;

    private String courseLevel;

    private int courseState;

    private Date releaseDate;

    @OneToOne(mappedBy = "courseInfo")
    private CourseImage courseImage;

    @OneToOne(mappedBy = "courseInfo")
    private CoursePrice coursePrice;

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
    private TeacherLogin teacherLogin;

    @ManyToOne
    @JoinColumn(name = "college_id",referencedColumnName = "collegeId")
    private CollegeInfo collegeInfo;

    @OneToMany(mappedBy = "courseInfo")
    private List<CourseVideo> courseVideoList = new ArrayList<>();

    @OneToMany(mappedBy = "courseInfo")
    private List<CourseComment> courseCommentList = new ArrayList<>();

    /*@ManyToMany(targetEntity = StudentLogin.class,mappedBy = "courseInfoList")
    private List<StudentLogin> studentLoginList = new ArrayList<>();*/


}
