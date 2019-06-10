package com.amadeus.framework.domain.student;

import com.amadeus.framework.domain.course.CourseInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_login", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class StudentLogin implements Serializable {

    private static final long serialVersionUID = -5873774557614827363L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String studentId;

    @Size(min = 11, max = 11, message = "手机号为11位")
    @Pattern(regexp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$", message = "手机号码格式错误")
    private String telephoneNumber;

    @Email(message = "邮箱格式错误")
    @Size(max = 50)
    private String email;

    @Size(max = 60, message = "请输入60位密码")
    private String password;

    private int accountState;

    private int activationState;

    @OneToOne(mappedBy = "studentLogin")
    private StudentInfo studentInfo;

    /*@ManyToMany(cascade = CascadeType.ALL, targetEntity = CourseInfo.class)
    @JoinTable(name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "studentId")},
            inverseJoinColumns = {@JoinColumn(name = "course_id",referencedColumnName = "courseId")})
    @JsonIgnore
    private List<CourseInfo> courseInfoList = new ArrayList<>();*/

}
