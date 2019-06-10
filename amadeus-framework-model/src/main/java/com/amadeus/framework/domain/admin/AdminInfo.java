package com.amadeus.framework.domain.admin;


import com.amadeus.framework.domain.college.CollegeInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_info", schema = "amadeus")
@EntityListeners(AuditingEntityListener.class)
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 7103233896567377977L;

    @Id
    @Size(min = 32, max = 32, message = "id为32位")
    private String adminId;

    @Size(min = 11, max = 11, message = "手机号为11位")
    @Pattern(regexp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$", message = "手机号码格式错误")
    private String telephoneNumber;

    @Email(message = "邮箱格式错误")
    @Size(max = 50)
    private String email;

    @Size(max = 60, message = "请输入60位密码")
    private String password;

    private String avatar;

    private String name;

    private int identity;

    private int accountState;

    private Date lastLoginTime;

    @OneToOne(mappedBy = "adminInfo")
    private CollegeInfo collegeInfo;
}
