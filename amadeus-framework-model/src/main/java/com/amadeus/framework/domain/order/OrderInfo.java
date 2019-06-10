package com.amadeus.framework.domain.order;

import com.amadeus.framework.domain.course.CourseInfo;
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
@Table(name = "order_info", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = -2402029106141409386L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String orderId;

    private String paymentMethod;

    private Double price;

    private Date orderTime;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "studentId")
    private StudentLogin studentLogin;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private CourseInfo courseInfo;
}
