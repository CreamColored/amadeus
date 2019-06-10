package com.amadeus.framework.domain.teacher;

import com.amadeus.framework.domain.college.CollegeInfo;
import com.amadeus.framework.domain.course.CourseComment;
import com.amadeus.framework.domain.course.CourseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_login", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class TeacherLogin implements Serializable {

    private static final long serialVersionUID = 322536420808177407L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String teacherId;

    private String telephoneNumber;

    private String email;

    private String password;

    private int accountState;

    private int activationState;

    @OneToOne(mappedBy = "teacherLogin")
    private TeacherInfo teacherInfo;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "collegeId")
    private CollegeInfo collegeInfo;

    @OneToMany(mappedBy = "teacherLogin")
    private Set<CourseInfo> courseInfoSet = new HashSet<>();

    @OneToMany(mappedBy = "studentLogin")
    private List<CourseComment> courseCommentList = new ArrayList<>();

}
