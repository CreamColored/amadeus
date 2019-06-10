package com.amadeus.framework.domain.student;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_info", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 5713585048525365730L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String infoId;

    @Size(max = 20)
    private String studentName;

    private int certificateType;

    @Size(max = 18)
    private String certificateCode;

    private String gender;

    private String province;

    private String city;

    private String avatar;

    private int userPoint;

    private BigDecimal userBalance;

    private Date birthday;

    private int memberLevel;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id",referencedColumnName = "studentId")
    private StudentLogin studentLogin;
}
