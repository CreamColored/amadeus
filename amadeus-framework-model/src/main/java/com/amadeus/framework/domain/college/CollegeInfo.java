package com.amadeus.framework.domain.college;

import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "college_info", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class CollegeInfo implements Serializable {

    private static final long serialVersionUID = 86401922917813377L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String collegeId;

    private String collegeName;

    private String collegeMotto;

    private String province;

    private String city;

    private String district;

    private int collegeState;

    private String collegeBadge;

    @Size(max = 100)
    private String description;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id", referencedColumnName = "adminId")
    private AdminInfo adminInfo;

    @OneToMany(mappedBy = "collegeInfo")
    @JsonIgnore
    private Set<TeacherLogin> teacherLoginSet = new HashSet<>();
}
