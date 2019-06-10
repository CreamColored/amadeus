package com.amadeus.framework.domain.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_course",schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 4356984603770477457L;

    @Id
    private String id;

    private String studentId;

    private String courseId;

}
