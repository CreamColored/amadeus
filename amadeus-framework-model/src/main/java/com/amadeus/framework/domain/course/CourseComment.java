package com.amadeus.framework.domain.course;

import com.amadeus.framework.domain.student.StudentLogin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_comment", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class CourseComment implements Serializable {

    private static final long serialVersionUID = 4359321483021167061L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String commentId;

    private String content;

    private Date publishTime;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private CourseInfo courseInfo;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private StudentLogin studentLogin;
}
