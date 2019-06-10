package com.amadeus.framework.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course_image", schema = "amadeus")
@EntityListeners(AuditListener.class)
public class CourseImage implements Serializable {

    private static final long serialVersionUID = -8780661118036039773L;

    @Id
    @Size(min = 32,max = 32,message = "id为32位")
    private String imageId;

    private String smallImage;

    private String mediumImage;

    private String bigImage;

    private String banner;

    private int isBanner;

    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private CourseInfo courseInfo;
}
