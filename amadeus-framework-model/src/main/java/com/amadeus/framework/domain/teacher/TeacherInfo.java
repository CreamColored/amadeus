package com.amadeus.framework.domain.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "teacher_info", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class TeacherInfo implements Serializable {

    private static final long serialVersionUID = -2507473873742448895L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String infoId;

    private String teacherName;

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
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
    private TeacherLogin teacherLogin;
}
