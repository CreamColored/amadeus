package com.amadeus.framework.domain.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_price", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class CoursePrice implements Serializable {

    private static final long serialVersionUID = 5822299263230256203L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String priceId;

    private int isFree;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    private Integer point;

    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private CourseInfo courseInfo;
}
